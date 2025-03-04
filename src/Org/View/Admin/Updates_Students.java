/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Org.View.Admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import Org.Ctrl.Sm_Ctrl;
import Org.Entity.Students_Entity;
import com.toedter.calendar.JDateChooser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

/**
 *
 * @author an
 */
public class Updates_Students extends javax.swing.JFrame {

    private final Sm_Ctrl controller;
    private final String regNumber;
    private final String id;
    private final String lname;
    private final String fname;
    private final String mail;
    private final String d_o_b;
    private final String gender;
    private final String addresses;
    private final String tel;
    private final String pass;
    private Integer sexe = 0;

    /**
     * Creates new form Updates_Students
     *
     * @param regNumber
     * @param id
     * @param lname
     * @param gender
     * @param mail
     * @param d_o_b
     * @param fname
     * @param addresses
     * @param tel
     * @param pass
     * @param controller
     */
    public Updates_Students(String regNumber, String id, String lname, String fname, String mail, String d_o_b, String gender, String addresses, String tel, String pass, Sm_Ctrl controller) {
        initComponents();
        this.regNumber = regNumber;
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.mail = mail;
        this.d_o_b = d_o_b;
        this.gender = gender;
        this.addresses = addresses;
        this.tel = tel;
        this.pass = pass;
        this.controller = controller;

        updateDetails(regNumber, lname, fname, mail, d_o_b, gender, addresses, tel, pass);
    }

    public static void updateDetails(String regNumber, String lname, String fname, String mail, String d_o_b, String gender, String addresses, String tel, String pass) {
        if (l_name != null) {
            l_name.setText("1- Nom : " + lname);
        }
        if (f_name != null) {
            f_name.setText("2- Prénom : " + fname);
        }
        if (dob != null) {
            dob.setText("3- Date de naissance : " + d_o_b);
        }
        if (sex != null) {
            if ("1".equals(gender)) {
                sex.setText("4- Sexe : Masculin");
            } else if ("2".equals(gender)) {
                sex.setText("4- Sexe : Féminin");
            }
        }

        if (email != null) {
            email.setText("5- E-mail : " + mail);
        }
        if (address != null) {
            address.setText("6- Adresse : " + addresses);
        }
        if (phone != null) {
            phone.setText("7- Téléphone : " + tel);
        }

    }

//    ---------------------------------------------------------------------
//    ---------------------------------------------------------------------
//   Nom :
//    ---------------------------------------------------------------------
//    ---------------------------------------------------------------------
    private void showNameEditDialogLastName() {
        JDialog dialog = new JDialog((Frame) null, "Modifier le Nom", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout(10, 10)); // Ajoute un espacement entre les panels
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher "Nom Actuel" centré en haut
        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel("Nom actuel : " + lname);
        label.setFont(new java.awt.Font("sansserif", Font.BOLD, 20));
        label.setForeground(new java.awt.Color(0, 0, 204));
        titlePanel.add(label);

        // Panel pour "Nouveau Nom : " + JTextField (alignés horizontalement)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Nouveau Nom : ");
        label2.setFont(new java.awt.Font("sansserif", Font.BOLD, 18));
        JTextField nameField = new JTextField(15);
        inputPanel.add(label2);
        inputPanel.add(nameField);

        // Panel pour les boutons "Annuler" et "Envoyer"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Envoyer");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Ajout des panels au dialog
        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton "Envoyer"
        saveButton.addActionListener(event -> {
            String newName = nameField.getText().trim();
            if (newName.split("\\s+").length > 2) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Le nom ne peut contenir que deux noms composés.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            String regex = "^[A-Za-z-]+$";

            String[] words = newName.split("\\s+");

            for (String word : words) {

                if (!word.matches(regex)) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Le nom ne peut contenir que des lettres et des tirets.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (!newName.isEmpty()) {
                // Créer l'objet Students_Entity avec les nouvelles données
                // Créer une instance avec le constructeur par défaut
                Students_Entity stu = new Students_Entity();

// Mise à jour de l'ID et du nom de famille
                stu.setId(Long.valueOf(id)); // Remplacer 'id' par l'identifiant de l'étudiant
                stu.setLast_name(newName); // Mettre à jour le nom

// Optionnellement, si d'autres champs doivent être modifiés, vous pouvez aussi les ajuster
                try {
                    // Mise à jour via le contrôleur
                    controller.updateLastNameStudents(stu);
                    JOptionPane.showMessageDialog(dialog, "Nom modifié avec succès !");

                    // Mettre à jour le label avec le nouveau nom
                    l_name.setText("1- Nom : " + newName);

                    // Ferme la fenêtre pop-up
                    dialog.dispose();

                    // Actualiser la page mère
                    refreshMainFrameLastName();
                } catch (Exception e) {
                    // En cas d'erreur, afficher un message d'erreur
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour du nom : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si le nom est vide, afficher une erreur
                JOptionPane.showMessageDialog(dialog, "Le nom ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Action pour le bouton "Annuler"
        cancelButton.addActionListener(event -> dialog.dispose());

        dialog.setVisible(true);
    }

// Méthode pour actualiser la page mère
    private void refreshMainFrameLastName() {
        SwingUtilities.getWindowAncestor(l_name).repaint();
    }

//    ---------------------------------------------------------------------
//    ---------------------------------------------------------------------
//   Prénom :
//    ---------------------------------------------------------------------
//    ---------------------------------------------------------------------    
    private void showNameEditDialogFirstName() {
        JDialog dialog = new JDialog((Frame) null, "Modifier le Prénom", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout(10, 10)); // Ajoute un espacement entre les panels
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher "Nom Actuel" centré en haut
        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel("Prénom actuel : " + fname);
        label.setFont(new java.awt.Font("sansserif", Font.BOLD, 20));
        label.setForeground(new java.awt.Color(0, 0, 204));
        titlePanel.add(label);

        // Panel pour "Nouveau Nom : " + JTextField (alignés horizontalement)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Nouveau prénom : ");
        label2.setFont(new java.awt.Font("sansserif", Font.BOLD, 18));
        JTextField nameField = new JTextField(15);
        inputPanel.add(label2);
        inputPanel.add(nameField);

        // Panel pour les boutons "Annuler" et "Envoyer"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Envoyer");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Ajout des panels au dialog
        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton "Envoyer"
        saveButton.addActionListener(event -> {
            String FirsName = nameField.getText().trim();
            if (FirsName.split("\\s+").length > 3) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Le Prénom ne peut contenir que deux noms composés.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            String regex = "^[A-Za-z-]+$";

            String[] words = FirsName.split("\\s+");

            for (String word : words) {

                if (!word.matches(regex)) {
                    javax.swing.JOptionPane.showMessageDialog(dialog, "Le Prénom ne peut contenir que des lettres et des tirets.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (!FirsName.isEmpty()) {
                // Créer l'objet Students_Entity avec les nouvelles données
                // Créer une instance avec le constructeur par défaut
                Students_Entity stu = new Students_Entity();

// Mise à jour de l'ID et du nom de famille
                stu.setId(Long.valueOf(id)); // Remplacer 'id' par l'identifiant de l'étudiant
                stu.setFirst_name(FirsName); // Mettre à jour le nom

// Optionnellement, si d'autres champs doivent être modifiés, vous pouvez aussi les ajuster
                try {
                    // Mise à jour via le contrôleur
                    controller.updateFirstNameStudents(stu);
                    JOptionPane.showMessageDialog(dialog, "Prénom modifié avec succès !");

                    // Mettre à jour le label avec le nouveau nom
                    f_name.setText("2- Prénom : " + FirsName);

                    // Ferme la fenêtre pop-up
                    dialog.dispose();

                    // Actualiser la page mère
                    refreshMainFrameFirstName();
                } catch (Exception e) {
                    // En cas d'erreur, afficher un message d'erreur
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour du Prénom : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si le nom est vide, afficher une erreur
                JOptionPane.showMessageDialog(dialog, "Le Prénom ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Action pour le bouton "Annuler"
        cancelButton.addActionListener(event -> dialog.dispose());

        dialog.setVisible(true);
    }

// Méthode pour actualiser la page mère
    private void refreshMainFrameFirstName() {
        SwingUtilities.getWindowAncestor(f_name).repaint();
    }

//----------------------------------------------
//----------------------------------------------
//        dob
//----------------------------------------------
//----------------------------------------------    
    private void showEditDialogDob() {
        JDialog dialog = new JDialog((Frame) null, "Modifier la date de naissance", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new BorderLayout(10, 10)); // Espacement entre les composants
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher le JDateChooser
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label = new JLabel("Nouvelle date de naissance : ");
        label.setFont(new Font("sansserif", Font.BOLD, 18));

        // Création du JDateChooser
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy"); // Format de date

        inputPanel.add(label);
        inputPanel.add(dateChooser);

        // Panel pour les boutons "Annuler" et "Enregistrer"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Enregistrer");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Action du bouton "Enregistrer"
        saveButton.addActionListener(e -> {
            Date selectedDate = dateChooser.getDate();
            if (selectedDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(selectedDate);

                try {
                    // Créer l'objet Students_Entity avec les nouvelles données
                    Students_Entity stu = new Students_Entity();
                    stu.setId(Long.valueOf(id)); // Remplacer 'id' par l'identifiant de l'étudiant
                    LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    stu.setDob(localDate);

                    // Mise à jour via le contrôleur
                    controller.updateDobStudents(stu);
                    JOptionPane.showMessageDialog(dialog, "Date de naissance modifiée avec succès !");

                    // Mettre à jour l'affichage de la date de naissance
                    dob.setText("3- Date de naissance : " + formattedDate);

                    // Ferme la fenêtre pop-up
                    dialog.dispose();

                    // Actualiser la page mère
                    refreshMainFrameDob();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour de la date de naissance : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Veuillez sélectionner une date.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action du bouton "Annuler"
        cancelButton.addActionListener(e -> dialog.dispose());

        // Ajout des panels au dialogue
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

// Méthode pour actualiser la page mère
    private void refreshMainFrameDob() {
        SwingUtilities.getWindowAncestor(dob).repaint();
    }

//----------------------------------------------
//----------------------------------------------
//        Sexe
//----------------------------------------------
//----------------------------------------------     
    private void showEditDialogSex() {
        JDialog dialog = new JDialog((Frame) null, "Sélectionner le sexe", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher les CheckBox
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JCheckBox checkBoxMasculin = new JCheckBox("Masculin");
        JCheckBox checkBoxFeminin = new JCheckBox("Féminin");

        // Logique : Sélection exclusive
        checkBoxMasculin.addActionListener(e -> {
            if (checkBoxMasculin.isSelected()) {
                checkBoxFeminin.setSelected(false);
            }
        });

        checkBoxFeminin.addActionListener(e -> {
            if (checkBoxFeminin.isSelected()) {
                checkBoxMasculin.setSelected(false);
            }
        });

        inputPanel.add(checkBoxMasculin);
        inputPanel.add(checkBoxFeminin);

        // Panel pour les boutons "Annuler" et "Valider"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton validateButton = new JButton("Envoyer");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(cancelButton);
        buttonPanel.add(validateButton);

        // Action du bouton "Valider"
        validateButton.addActionListener(e -> {
            if (!checkBoxMasculin.isSelected() && !checkBoxFeminin.isSelected()) {
                JOptionPane.showMessageDialog(dialog, "Veuillez sélectionner une option.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newSex = checkBoxMasculin.isSelected() ? 1 : 2; // 1 = Masculin, 2 = Féminin

            // Création de l'objet Student_Entity et mise à jour des données
            Students_Entity stu = new Students_Entity();
            stu.setId(Long.valueOf(id)); // Remplace 'id' par l'identifiant de l'étudiant
            stu.setSex(newSex); // Mise à jour du sexe

            try {
                // Mise à jour via le contrôleur
                controller.updateSexStudents(stu);
                JOptionPane.showMessageDialog(dialog, "Sexe modifié avec succès !");

                // Mettre à jour le label avec la nouvelle valeur
                sex.setText("4- Sexe : " + (newSex == 1 ? "Masculin" : "Féminin"));

                // Fermer la fenêtre pop-up
                dialog.dispose();

                // Actualiser la page mère
                refreshMainFrameSex();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour du sexe : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action du bouton "Annuler"
        cancelButton.addActionListener(e -> dialog.dispose());

        // Ajout des composants au dialogue
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void refreshMainFrameSex() {
        SwingUtilities.getWindowAncestor(sex).repaint();
    }

//----------------------------------------------
//----------------------------------------------
//         E-mail
//----------------------------------------------
//----------------------------------------------    
    private void showNameEditDialogEmail() {
        JDialog dialog = new JDialog((Frame) null, "Modifier l'email", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout(10, 10)); // Ajoute un espacement entre les panels
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher "Nom Actuel" centré en haut
        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel("E-mail actuel : " + mail);
        label.setFont(new java.awt.Font("sansserif", Font.BOLD, 20));
        label.setForeground(new java.awt.Color(0, 0, 204));
        titlePanel.add(label);

        // Panel pour "Nouveau Nom : " + JTextField (alignés horizontalement)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Nouveau e-mail : ");
        label2.setFont(new java.awt.Font("sansserif", Font.BOLD, 18));
        JTextField nameField = new JTextField(15);
        inputPanel.add(label2);
        inputPanel.add(nameField);

        // Panel pour les boutons "Annuler" et "Envoyer"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Envoyer");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Ajout des panels au dialog
        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton "Envoyer"
        saveButton.addActionListener(event -> {
            String newEmail = nameField.getText().trim();

            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!Pattern.matches(emailRegex, newEmail)) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Adresse email invalide.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newEmail.isEmpty()) {
                // Créer un objet Students_Entity et mettre à jour l'ID et l'email
                Students_Entity stu = new Students_Entity();
                stu.setId(Long.valueOf(id)); // Remplacer 'id' par l'identifiant de l'étudiant
                stu.setEmail(newEmail); // Mettre à jour l'email

                try {
                    // Tenter la mise à jour de l'e-mail via le contrôleur
                    boolean success = controller.updateEmailStudents(stu);

                    if (success) {
                        // Mise à jour réussie uniquement si l'e-mail n'existe pas déjà
                        JOptionPane.showMessageDialog(dialog, "E-mail modifié avec succès !");
                        email.setText("5- E-mail : " + newEmail); // Mise à jour de l'affichage du label
                        dialog.dispose(); // Fermer la fenêtre
                        refreshMainFrameEmail(); // Rafraîchir la vue principale
                    }
                } catch (IllegalArgumentException e) {
                    // Afficher l'erreur en cas d'ID null
                    JOptionPane.showMessageDialog(dialog, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (PersistenceException e) {
                    // Gestion des erreurs de contrainte d'unicité
                    String message = e.getMessage().toLowerCase();
                    if (message.contains("unique") || message.contains("duplicate")) {
                        JOptionPane.showMessageDialog(dialog, "Cet e-mail existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    e.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(dialog, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si l'email est vide, afficher une erreur
                JOptionPane.showMessageDialog(dialog, "L'e-mail ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Action pour le bouton "Annuler"
        cancelButton.addActionListener(event -> dialog.dispose());

        dialog.setVisible(true);
    }

// Méthode pour actualiser la page mère
    private void refreshMainFrameEmail() {
        SwingUtilities.getWindowAncestor(email).repaint();
    }

//----------------------------------------------
//----------------------------------------------
//          Adresse
//----------------------------------------------
//----------------------------------------------    
    private void showNameEditDialogAddress() {
        JDialog dialog = new JDialog((Frame) null, "Modifier l'addresse", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout(10, 10)); // Ajoute un espacement entre les panels
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher "Nom Actuel" centré en haut
        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel("Addresse actuel : " + addresses);
        label.setFont(new java.awt.Font("sansserif", Font.BOLD, 20));
        label.setForeground(new java.awt.Color(0, 0, 204));
        titlePanel.add(label);

        // Panel pour "Nouveau Nom : " + JTextField (alignés horizontalement)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Nouvelle Adresse : ");
        label2.setFont(new java.awt.Font("sansserif", Font.BOLD, 18));
        JTextField nameField = new JTextField(15);
        inputPanel.add(label2);
        inputPanel.add(nameField);

        // Panel pour les boutons "Annuler" et "Envoyer"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Envoyer");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Ajout des panels au dialog
        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton "Envoyer"
        saveButton.addActionListener(event -> {
            String newAddress = nameField.getText().trim();

            if (!newAddress.isEmpty()) {
                // Créer l'objet Students_Entity avec les nouvelles données
                // Créer une instance avec le constructeur par défaut
                Students_Entity stu = new Students_Entity();

// Mise à jour de l'ID et du nom de famille
                stu.setId(Long.valueOf(id)); // Remplacer 'id' par l'identifiant de l'étudiant
                stu.setAddress(newAddress);

// Optionnellement, si d'autres champs doivent être modifiés, vous pouvez aussi les ajuster
                try {
                    // Mise à jour via le contrôleur
                    controller.updateAddressStudents(stu);
                    JOptionPane.showMessageDialog(dialog, "Addresse modifié avec succès !");

                    // Mettre à jour le label avec le nouveau nom
                    address.setText("6- Addresse: " + newAddress);

                    // Ferme la fenêtre pop-up
                    dialog.dispose();

                    // Actualiser la page mère
                    refreshMainFrameAddress();
                } catch (Exception e) {
                    // En cas d'erreur, afficher un message d'erreur
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour de l'addresse : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si le nom est vide, afficher une erreur
                JOptionPane.showMessageDialog(dialog, "L'addresse ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Action pour le bouton "Annuler"
        cancelButton.addActionListener(event -> dialog.dispose());

        dialog.setVisible(true);
    }

// Méthode pour actualiser la page mère
    private void refreshMainFrameAddress() {
        SwingUtilities.getWindowAncestor(address).repaint();
    }

//----------------------------------------------
//----------------------------------------------
//          Phone
//----------------------------------------------
//----------------------------------------------    
    private void showEditDialogPhone() {
        JDialog dialog = new JDialog((Frame) null, "Modifier le numéro de téléphone", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout(10, 10)); // Ajoute un espacement entre les panels
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher "Nom Actuel" centré en haut
        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel("Téléphone actuel : " + tel);
        label.setFont(new java.awt.Font("sansserif", Font.BOLD, 20));
        label.setForeground(new java.awt.Color(0, 0, 204));
        titlePanel.add(label);

        // Panel pour "Nouveau Nom : " + JTextField (alignés horizontalement)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Nouveau téléphone : ");
        label2.setFont(new java.awt.Font("sansserif", Font.BOLD, 18));
        JTextField nameField = new JTextField(15);
        inputPanel.add(label2);
        inputPanel.add(nameField);

        // Panel pour les boutons "Annuler" et "Envoyer"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Envoyer");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Ajout des panels au dialog
        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton "Envoyer"
        saveButton.addActionListener(event -> {
            String phone_input = nameField.getText().trim();

            int newphone;
            if (!phone_input.matches("\\d{8}")) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Le numéro de téléphone doit contenir 8 chiffres.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                newphone = Integer.parseInt(phone_input);
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(dialog, "Numéro de téléphone invalide.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!phone_input.isEmpty()) {
                // Créer un objet Students_Entity et mettre à jour l'ID et l'email
                Students_Entity stu = new Students_Entity();
                stu.setId(Long.valueOf(id)); // Remplacer 'id' par l'identifiant de l'étudiant
                stu.setPhone(newphone); // Mettre à jour l'email

                try {
                    // Tenter la mise à jour de l'e-mail via le contrôleur
                    boolean success = controller.updatePhoneStudents(stu);

                    if (success) {
                        // Mise à jour réussie uniquement si l'e-mail n'existe pas déjà
                        JOptionPane.showMessageDialog(dialog, "Numéro de téléphone modifié avec succès !");
                        phone.setText("7- Téléphone : " + newphone); // Mise à jour de l'affichage du label
                        dialog.dispose(); // Fermer la fenêtre
                        refreshMainFramePhone(); // Rafraîchir la vue principale
                    }
                } catch (IllegalArgumentException e) {
                    // Afficher l'erreur en cas d'ID null
                    JOptionPane.showMessageDialog(dialog, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (PersistenceException e) {
                    // Gestion des erreurs de contrainte d'unicité
                    String message = e.getMessage().toLowerCase();
                    if (message.contains("unique") || message.contains("duplicate")) {
                        JOptionPane.showMessageDialog(dialog, "Ce numéro de téléphone existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    e.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(dialog, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si l'email est vide, afficher une erreur
                JOptionPane.showMessageDialog(dialog, "Le numéro de téléphone ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Action pour le bouton "Annuler"
        cancelButton.addActionListener(event -> dialog.dispose());

        dialog.setVisible(true);
    }

// Méthode pour actualiser la page mère
    private void refreshMainFramePhone() {
        SwingUtilities.getWindowAncestor(phone).repaint();
    }
//----------------------------------------------
//----------------------------------------------
//          Mots de passe
//----------------------------------------------
//----------------------------------------------    

    private void showEditDialogPassword() {
        JDialog dialog = new JDialog((Frame) null, "Modifier le mot de passe", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setLocationRelativeTo(null);

        // Génération du mot de passe
        String generatedPassword = generateRandomPassword();
        JLabel passwordLabel = new JLabel("Mot de passe généré : " + generatedPassword, SwingConstants.CENTER);

        // Panel pour afficher le mot de passe
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(passwordLabel);

        // Boutons "Annuler", "Confirmer" et "Régénérer"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton confirmButton = new JButton("Confirmer");
        JButton regenerateButton = new JButton("Régénérer");
        JButton cancelButton = new JButton("Annuler");

        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(regenerateButton);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour confirmer
        confirmButton.addActionListener(e -> {

            String hashedPassword = hashPassword(generatedPassword);
            if (hashedPassword != null) {
                // Création de l'objet étudiant
                Students_Entity stu = new Students_Entity();
                stu.setId(Long.valueOf(id));
                stu.setPassword(hashedPassword); // Mise à jour du mot de passe

                try {
                    // Mise à jour via le contrôleur
                    controller.updatePasswordStudents(stu);
                    JOptionPane.showMessageDialog(dialog, "Mot de passe modifié avec succès !");

                    // Ferme la fenêtre pop-up
                    dialog.dispose();

                    // Actualiser la page mère (si nécessaire)
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour du mot de passe : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Erreur : ID utilisateur invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action pour régénérer un nouveau mot de passe
        regenerateButton.addActionListener(e -> {
            dialog.dispose();
            showEditDialogPassword(); // Régénérer avec un nouvel ID
        });

        // Action pour annuler
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

// Méthode pour générer un mot de passe aléatoire
    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        SecureRandom random = new SecureRandom();
        int length = 4 + random.nextInt(12); // Entre 4 et 15 caractères
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }

// Méthode pour hacher le mot de passe en SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Erreur lors du hachage du mot de passe : " + e.getMessage());
            return null;
        }
    }

//----------------------------------------------
//----------------------------------------------
//          Matricule
//----------------------------------------------
//----------------------------------------------    
    public void showEditDialogRegNumb() {
        JDialog dialog = new JDialog((Frame) null, "Modifier la Matricule", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher "Matricule Actuel" centré en haut
        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel("Matricule actuel : " + regNumber);
        label.setFont(new java.awt.Font("sansserif", Font.BOLD, 20));
        label.setForeground(new java.awt.Color(0, 0, 204));
        titlePanel.add(label);

        // Panel pour "Nouvelle Matricule" générée automatiquement
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Nouvelle matricule : ");
        label2.setFont(new java.awt.Font("sansserif", Font.BOLD, 18));
        JTextField regField = new JTextField(15);
        regField.setEditable(false);

        // Génération d'une nouvelle matricule unique
        String newRegNumber = controller.generateUniqueRegNumber();
        regField.setText(newRegNumber);

        inputPanel.add(label2);
        inputPanel.add(regField);

        // Panel pour les boutons "Annuler" et "Confirmer"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Confirmer");
        JButton cancelButton = new JButton("Annuler");
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Ajout des panels au dialog
        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton "Confirmer"
        saveButton.addActionListener(event -> {
            try {
                Students_Entity stu = new Students_Entity();
                stu.setId(Long.valueOf(id)); // Assurez-vous que l'ID est valide
                stu.setReg_number(newRegNumber);

                boolean success = controller.updateRegNumber(stu);

                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Matricule modifiée avec succès !");

                    dialog.dispose();

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(dialog, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action pour le bouton "Annuler"
        cancelButton.addActionListener(event -> dialog.dispose());

        dialog.setVisible(true);
    }

// Méthode pour générer un mot de passe aléatoire
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l_name = new javax.swing.JLabel();
        f_name = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        dob = new javax.swing.JLabel();
        sex = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        reg_numb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Org/Images/mortier(4).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Élément à modfier");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(51, 102, 255)));

        l_name.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        l_name.setForeground(new java.awt.Color(0, 0, 204));
        l_name.setText("1- Nom :");
        l_name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (l_name != null) {
                    showNameEditDialogLastName();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                l_name.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                l_name.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                l_name.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                l_name.setForeground(new java.awt.Color(0, 0, 204));

            }
        });

        f_name.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        f_name.setForeground(new java.awt.Color(0, 0, 204));
        f_name.setText("2- Prénom :");
        f_name.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (f_name != null) {
                    showNameEditDialogFirstName();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                f_name.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                f_name.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                f_name.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                f_name.setForeground(new java.awt.Color(0, 0, 204));
            }
        });

        password.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        password.setForeground(new java.awt.Color(0, 0, 204));
        password.setText("8- Régénérer le mot de passe ");
        password.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (password != null) {
                    showEditDialogPassword();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                password.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                password.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                password.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                password.setForeground(new java.awt.Color(0, 0, 204));
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 102, 255));
        jButton2.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Retour");
        jButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jButton2.setBackground(new java.awt.Color(0,0,0));
                jButton2.setForeground(new java.awt.Color(51, 102, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jButton2.setBackground(new java.awt.Color(51, 102, 255));
                jButton2.setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        dob.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        dob.setForeground(new java.awt.Color(0, 0, 204));
        dob.setText("3- Date de naissance :");
        dob.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (dob != null) {
                    showEditDialogDob();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dob.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                dob.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                dob.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                dob.setForeground(new java.awt.Color(0, 0, 204));
            }
        });

        sex.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        sex.setForeground(new java.awt.Color(0, 0, 204));
        sex.setText("4- Sexe :");
        sex.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (sex != null) {
                    showEditDialogSex();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                sex.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                sex.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                sex.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                sex.setForeground(new java.awt.Color(0, 0, 204));
            }
        });

        email.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        email.setForeground(new java.awt.Color(0, 0, 204));
        email.setText("5- E-mail :");
        email.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (address != null) {
                    showNameEditDialogEmail();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                email.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                email.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                email.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                email.setForeground(new java.awt.Color(0, 0, 204));
            }
        });

        address.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        address.setForeground(new java.awt.Color(0, 0, 204));
        address.setText("6- Addresse :");
        address.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (address != null) {
                    showNameEditDialogAddress();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                address.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                address.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                address.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                address.setForeground(new java.awt.Color(0, 0, 204));
            }
        });

        phone.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        phone.setForeground(new java.awt.Color(0, 0, 204));
        phone.setText("7- Téléphone :");
        phone.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (phone != null) {
                    showEditDialogPhone();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                phone.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                phone.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                phone.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                phone.setForeground(new java.awt.Color(0, 0, 204));
            }
        });

        reg_numb.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        reg_numb.setForeground(new java.awt.Color(0, 0, 204));
        reg_numb.setText("9- Régénérer la matricule");
        reg_numb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (address != null) {
                    showEditDialogRegNumb();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                reg_numb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                reg_numb.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                reg_numb.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                reg_numb.setForeground(new java.awt.Color(0, 0, 204));
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 127, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(114, 114, 114))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address)
                            .addComponent(phone)
                            .addComponent(password)
                            .addComponent(email)
                            .addComponent(sex)
                            .addComponent(dob)
                            .addComponent(f_name)
                            .addComponent(l_name)
                            .addComponent(reg_numb))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(l_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(f_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dob)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(address)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reg_numb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Sm_Ctrl ctx = new Sm_Ctrl();
        dispose();
        Dashboard_All_Students AllStudentsFrame = new Dashboard_All_Students(ctx);
        AllStudentsFrame.setLocationRelativeTo(null);
        AllStudentsFrame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Updates_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Updates_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Updates_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Updates_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sm_Ctrl xt = new Sm_Ctrl();
                new Updates_Students("", "", "", "", "", "", "", "", "", "", xt).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel address;
    private static javax.swing.JLabel dob;
    private static javax.swing.JLabel email;
    private static javax.swing.JLabel f_name;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private static javax.swing.JLabel l_name;
    private static javax.swing.JLabel password;
    private static javax.swing.JLabel phone;
    private static javax.swing.JLabel reg_numb;
    private static javax.swing.JLabel sex;
    // End of variables declaration//GEN-END:variables
}
