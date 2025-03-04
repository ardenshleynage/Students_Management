/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Org.View.Admin;

import Org.Ctrl.Sm_Ctrl;
import Org.Entity.Teachers_Entity;
import Org.View.Component.Dash;
import Org.View.Component.Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author an
 */
public class ShowResultSearchTeachers extends javax.swing.JFrame {

    private final Sm_Ctrl controller;

    /**
     * Creates new form ShowResultSearchTeachers
     *
     * @param controller
     * @param teacher
     */
    public ShowResultSearchTeachers(Sm_Ctrl controller, Teachers_Entity teacher) {
        this.controller = controller;
        initComponents();
        Dash dashPanel = new Dash();
        jPanel1.setLayout(new java.awt.BorderLayout()); // Remplir tout le panel
        jPanel1.add(dashPanel, BorderLayout.CENTER);

        // Modifier les couleurs en fonction de la JFrame
        dashPanel.changerCouleurLabel3(Color.WHITE); // Rouge par exemple

        // Rafraîchir l'affichage
        jPanel1.revalidate();
        jPanel1.repaint();

        // Centrer la fenêtre
        setLocationRelativeTo(null);

        Menu MenuPanel = new Menu();
        jPanel3.setLayout(new java.awt.BorderLayout()); // Remplir tout le panel
        jPanel3.add(MenuPanel, BorderLayout.CENTER);

        // Modifier les couleurs en fonction de la JFrame
        MenuPanel.MenuLabel(Color.blue);

        // Rafraîchir l'affichage
        jPanel3.revalidate();
        jPanel3.repaint();
        if (teacher != null) {
            populateTable(teacher);
        }
        ClickStudents();
    }

    public void populateTable(Teachers_Entity teacher) {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0); // Effacer les anciennes données

        if (teacher == null) {
            String emptyMessage = "Aucun enseignant trouvé.";
            model.setColumnCount(1);
            model.setColumnIdentifiers(new String[]{""});
            model.addRow(new Object[]{emptyMessage});
            jTable2.setEnabled(false);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            jTable2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        } else {

            int rowNumber = 1;
            String statut = (teacher.getAccess() == 1) ? "Autoriser" : (teacher.getAccess() == 2) ? "Bloquer" : (teacher.getAccess() == 0) ? "Supprimer" : "Inconnu";
            String sex = (teacher.getSex() == 1) ? "Masculin" : (teacher.getSex() == 2) ? "Féminin" : "Inconnu";

            Object[] rowData = {
                rowNumber++, // Numéro de ligne
                teacher.getReg_number(),
                teacher.getId(),
                teacher.getLast_name(),
                teacher.getFirst_name(),
                teacher.getEmail(),
                teacher.getDob(),
                statut,
                sex,
                teacher.getSpeciality(),
                teacher.getPhone(),
                teacher.getReg_date(),
                teacher.getPassword(),
            };

            model.addRow(rowData);

            // Cacher la colonne ID
            jTable2.getColumnModel().getColumn(2).setMinWidth(0);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(2).setWidth(0);
            
            jTable2.getColumnModel().getColumn(12).setMinWidth(0);
            jTable2.getColumnModel().getColumn(12).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(12).setWidth(0);
        }
    }

    private void ClickStudents() {
        jTable2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable2.getSelectedRow();
                if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(e)) {
                    String regNumb = jTable2.getValueAt(selectedRow, 1).toString();
                    try {
                        
                        Teachers_Entity teacher = controller.findTeachersByRegNumber(regNumb);
                        

                        if (teacher != null) {
                            String regNumber = jTable2.getValueAt(selectedRow, 1).toString(); // Matricule
                            String number = jTable2.getValueAt(selectedRow, 2).toString();
                            String lname = jTable2.getValueAt(selectedRow, 3).toString(); // Nom
                            String fname = jTable2.getValueAt(selectedRow, 4).toString(); // Prénom
                            String mail = jTable2.getValueAt(selectedRow, 5).toString(); // Email
                            LocalDate dob = LocalDate.parse(jTable2.getValueAt(selectedRow, 6).toString()); // Date de naissance
                            String access = jTable2.getValueAt(selectedRow, 7).toString().trim();
                            String wtf = jTable2.getValueAt(selectedRow, 8).toString(); // Sexe
                            String speciality = jTable2.getValueAt(selectedRow, 9).toString(); // Adresse
                            int phone = Integer.parseInt(jTable2.getValueAt(selectedRow, 10).toString()); // Téléphone
                            LocalDate regDate = LocalDate.parse(jTable2.getValueAt(selectedRow, 11).toString()); // Date d'enregistrement
                            String password = jTable2.getValueAt(selectedRow, 12).toString();

//                            int sex = Integer.parseInt(wtf);
//                            String statut = (statuts == 1) ? "Autorisé" : (statuts == 2) ? "Bloqué" : "Inconnu";
//                            String gender = (sex == 1) ? "Masculin" : (sex == 2) ? "Féminin" : "Inconnu";
                            String message = String.format(
                                    "Matricule : %s\nNom : %s\nPrénom : %s\nE-Mail : %s\nDate de naissance : %s\nSexe : %s\nSpécialité : %s\nTéléphone : %s\nDate d'enregistrement : %s\nStatut : %s\n",
                                    regNumber, lname, fname, mail, dob, wtf, speciality, phone, regDate, access
                            );

                            // Définir les options dynamiques en fonction du statut
                            Object[] options;

                            if (access.equals("Autoriser")) {
                                options = new Object[]{"Attribuer un cours", "Afficher les cours", "Bloquer", "Modifier", "Supprimer", "Annuler"};
                            } else if (access.equals("Supprimer")) {
                                options = new Object[]{"Attribuer un cours", "Afficher les cours", "Autoriser", "Modifier", "Restaurer", "Annuler"};
                            } else if (access.equals("Bloquer")) {
                                options = new Object[]{"Attribuer un cours", "Afficher les cours", "Autoriser", "Modifier", "Supprimer", "Annuler"};
                            } else {
                                options = new Object[]{"Annuler"}; // Option par défaut si `statuts` a une valeur inattendue
                            }

                            int option = JOptionPane.showOptionDialog(
                                    ShowResultSearchTeachers.this,
                                    message,
                                    "Détails de l'enseignant",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null,
                                    options,
                                    "Annuler"
                            );

                            // Actions basées sur l'option sélectionnée
                            switch (option) {
                                case 0:
                                    Sm_Ctrl cre = new Sm_Ctrl();
                                    dispose();
                                    Add_Classes_Teachers ACTFrame = new Add_Classes_Teachers(number,lname,fname,speciality,cre);
                                    ACTFrame.setLocationRelativeTo(null);
                                    ACTFrame.setVisible(true);

                                    break;

                                case 1: // "Bloquer" ou "Autoriser"
                                    CourseListDialog.showDialog(ShowResultSearchTeachers.this, teacher.getId(), controller);

                                    break;

                                case 2:
                                    if (access == "Autoriser") {
                                        controller.BlockTeachers(teacher);
                                        JOptionPane.showMessageDialog(ShowResultSearchTeachers.this, "Enseignant bloqué avec succès !");
                                        Sm_Ctrl contra = new Sm_Ctrl();
                                        dispose();
                                        Dashboard_All_Teachers AllteacherFrame = new Dashboard_All_Teachers(contra);
                                        AllteacherFrame.setLocationRelativeTo(null);
                                        AllteacherFrame.setVisible(true);
                                    } else {
                                        controller.AllowTeachers(teacher);
                                        JOptionPane.showMessageDialog(ShowResultSearchTeachers.this, "Enseignat autorisé avec succès !");
                                        Sm_Ctrl contrx = new Sm_Ctrl();
                                        dispose();
                                        Dashboard_All_Teachers AllteacherFrame = new Dashboard_All_Teachers(contrx);
                                        AllteacherFrame.setLocationRelativeTo(null);
                                        AllteacherFrame.setVisible(true);
                                    }
                                    break;

                                case 3:
                                    dispose();
                                    String sexe = String.valueOf(teacher.getSex());
                                    String tel = String.valueOf(teacher.getPhone());
                                    String d_o_b = String.valueOf(teacher.getDob());
                                    Sm_Ctrl cx = new Sm_Ctrl();
                                    Updates_Teachers UpdateFrame = new Updates_Teachers(regNumber, number, lname, fname, mail, d_o_b, sexe, speciality, tel, password, cx);
                                    UpdateFrame.setLocationRelativeTo(null);
                                    UpdateFrame.setVisible(true);
                                    break;
                                case 4:
                                    if (access == "Autoriser" || access == "Bloquer") {
                                        DeleteTeacherQuestion(teacher);
                                    } else {
                                        controller.RestoreTeachers(teacher);
                                        JOptionPane.showMessageDialog(ShowResultSearchTeachers.this, "Ensegnant réstauré avec succès !");
                                        Sm_Ctrl contrc = new Sm_Ctrl();
                                        dispose();
                                        Dashboard_All_Teachers AllteacherFrame = new Dashboard_All_Teachers(contrc);
                                        AllteacherFrame.setLocationRelativeTo(null);
                                        AllteacherFrame.setVisible(true);
                                        
                                    }
                                    break;
                                case 5:
                                    break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(ShowResultSearchTeachers.this, "Erreur : l'enseignant sélectionné n'a pas pu être trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ShowResultSearchTeachers.this, "Erreur lors de la récupération des données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });
    }

    private void DeleteTeacherQuestion(Teachers_Entity teacher) {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement supprimez l'enseignant ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Non"
        );

        // Handle user selection
        if (option == JOptionPane.YES_OPTION) {
            handleDeleteTeachers(teacher);
        } else if (option == JOptionPane.NO_OPTION) {

            return;
        } else {
            // Handle any unexpected option, though in this case it's unlikely
            JOptionPane.showMessageDialog(null,
                    "Erreur : choix non reconnu.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void handleDeleteTeachers(Teachers_Entity teacher) {
        boolean success = controller.SureDeleteTeachers(teacher);

        if (success) {
            JOptionPane.showMessageDialog(this, "L'enseignant a été supprimés.", "Information", JOptionPane.INFORMATION_MESSAGE);
            Sm_Ctrl contrx = new Sm_Ctrl();
            dispose();
            Dashboard_All_Teachers AllteacherFrame = new Dashboard_All_Teachers(contrx);
            AllteacherFrame.setLocationRelativeTo(null);
            AllteacherFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion de l'enseignant.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showSearchDialog() {
        JDialog dialog = new JDialog((Frame) null, "Recherche par matricule", true);
        dialog.setSize(400, 150);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setLocationRelativeTo(null);

        // Panel pour l'entrée utilisateur
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Entrer la matricule : ");
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
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Action pour le bouton "Envoyer"
        saveButton.addActionListener(event -> {
            String regnumb = nameField.getText().trim();

            if (!regnumb.isEmpty()) {
                Teachers_Entity teacher = controller.findTeachersByRegNumber(regnumb);

                if (teacher != null) {
                    dialog.dispose(); // Fermer la boîte de dialogue
                    dispose(); // Fermer la fenêtre principale
                    Sm_Ctrl cx = new Sm_Ctrl();
                    // Ouvrir la nouvelle fenêtre avec les résultats
                    ShowResultSearchTeachers searchFrame = new ShowResultSearchTeachers(cx, teacher);
                    searchFrame.setLocationRelativeTo(null);
                    searchFrame.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(dialog,
                            "Aucun enseignant trouvé pour la recherche : " + regnumb,
                            "Résultat introuvable",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "La matricule ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action pour le bouton "Annuler"
        cancelButton.addActionListener(event -> dialog.dispose());

        dialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Org/Images/all(2)_sub.png"))); // NOI18N
        ImageIcon normalIcon = new ImageIcon(getClass().getResource("/Org/Images/all(2)_sub.png"));
        ImageIcon hoverIcon = new ImageIcon(getClass().getResource("/Org/Images/all(1)_sub.png"));
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl contrx = new Sm_Ctrl();
                dispose();
                Dashboard_All_Teachers AllTeachersFrame = new Dashboard_All_Teachers(contrx);
                AllTeachersFrame.setLocationRelativeTo(null);
                AllTeachersFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel5.setBackground(new java.awt.Color(51,102,255));
                jLabel5.setIcon(hoverIcon);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel5.setBackground(new java.awt.Color(255,255,255));
                jLabel5.setIcon(normalIcon);

            }
        });

        jLabel11.setBackground(new java.awt.Color(51, 102, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Org/Images/search-engine-optimization2.png"))); // NOI18N
        ImageIcon normalIcon1 = new ImageIcon(getClass().getResource("/Org/Images/search-engine-optimization2.png"));
        ImageIcon hoverIcon1 = new ImageIcon(getClass().getResource("/Org/Images/search-engine-optimization1.png"));
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jLabel11.setOpaque(true);
        jLabel11.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (jLabel11 != null) {
                    showSearchDialog();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel11.setBackground(new java.awt.Color(51,102,255));
                jLabel11.setIcon(hoverIcon1);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel11.setBackground(new java.awt.Color(51,102,255));
                jLabel11.setIcon(normalIcon1);

            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Org/Images/trash(1)_sub.png"))); // NOI18N
        ImageIcon normalIcon3 = new ImageIcon(getClass().getResource("/Org/Images/trash(1)_sub.png"));
        ImageIcon hoverIcon3 = new ImageIcon(getClass().getResource("/Org/Images/trash_sub.png"));
        jLabel13.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        jLabel13.setOpaque(true);
        jLabel13.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl contry = new Sm_Ctrl();
                dispose();
                Dashboard_Trash_Teachers TrashteachersFrame = new Dashboard_Trash_Teachers(contry);
                TrashteachersFrame.setLocationRelativeTo(null);
                TrashteachersFrame.setVisible(true);

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel13.setBackground(new java.awt.Color(51,102,255));
                jLabel13.setIcon(hoverIcon3);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel13.setBackground(new java.awt.Color(255, 255, 255));
                jLabel13.setIcon(normalIcon3);

            }
        });

        jTable2.setBackground(new java.awt.Color(51, 102, 255));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Matricule", "id", "Nom", "Prénom", "E-mail", "Date de naissance", "Statut", "Sexe", "Spécialité", "Téléphone", "Date d'enregistrement", "Mots de passe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(0, 0, 0));
        jTable2.setRowHeight(40);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(377, 377, 377))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(208, 208, 208))))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ShowResultSearchTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowResultSearchTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowResultSearchTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowResultSearchTeachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sm_Ctrl controllers = new Sm_Ctrl();
                Teachers_Entity te = new Teachers_Entity();
                new ShowResultSearchTeachers(controllers, te).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
