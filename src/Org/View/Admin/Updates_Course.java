/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Org.View.Admin;

import Org.Ctrl.Sm_Ctrl;
import Org.Entity.Course_Entity;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.persistence.PersistenceException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author an
 */
public class Updates_Course extends javax.swing.JFrame {

    private final Sm_Ctrl controller;
    private final String id;
    private final String name_course;
    private final String description_course;

    /**
     * Creates new form Updates_Course
     *
     * @param id
     * @param name_course
     * @param description_course
     * @param controller
     */
    public Updates_Course(String id, String name_course, String description_course, Sm_Ctrl controller) {
        initComponents();
        this.controller = controller;
        this.id = id;
        this.name_course = name_course;
        this.description_course = description_course;
        updateDetails(name_course, description_course);
    }

    public static void updateDetails(String name_course, String description_course) {
        if (name != null) {
            name.setText("1- Nom : " + name_course);
        }
        if (description != null) {
            description.setText("2- Description : " + description_course);
        }

    }

//    ---------------------------------------------------------------------
//    ---------------------------------------------------------------------
//   Nom :
//    ---------------------------------------------------------------------
//    ---------------------------------------------------------------------
    private void showNameEditDialog() {
        JDialog dialog = new JDialog((Frame) null, "Modifier le noml", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout(10, 10)); // Ajoute un espacement entre les panels
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher "Nom Actuel" centré en haut
        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel("Nom actuel : " + name_course);
        label.setFont(new java.awt.Font("sansserif", Font.BOLD, 20));
        label.setForeground(new java.awt.Color(0, 0, 204));
        titlePanel.add(label);

        // Panel pour "Nouveau Nom : " + JTextField (alignés horizontalement)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Nouveau nom : ");
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

            if (newName.length() > 250) {
                javax.swing.JOptionPane.showMessageDialog(this, "Le nom du cours ne peut pas dépasser 250 caractères.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (newName.split("\\s+").length > 5) {
                javax.swing.JOptionPane.showMessageDialog(this, "Le nom du cours ne peut contenir que cinq noms composés.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            String regex = "^[A-Za-z-]+$";

            String[] words = newName.split("\\s+");

            for (String word : words) {

                if (!word.matches(regex)) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Le nom ne peut contenir que des lettres et des tirets.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (!newName.isEmpty()) {
                // Créer un objet Students_Entity et mettre à jour l'ID et l'email
                Course_Entity cou = new Course_Entity();
                cou.setId(Long.valueOf(id)); // Remplacer 'id' par l'identifiant de l'étudiant
                cou.setName(newName); // Mettre à jour l'email

                try {
                    // Tenter la mise à jour de l'e-mail via le contrôleur
                    boolean success = controller.updateNameCourse(cou);

                    if (success) {
                        // Mise à jour réussie uniquement si l'e-mail n'existe pas déjà
                        JOptionPane.showMessageDialog(dialog, "Nom modifié avec succès !");
                        name.setText("1- Nom : " + newName); // Mise à jour de l'affichage du label
                        dialog.dispose(); // Fermer la fenêtre
                        refreshMainFrameName(); // Rafraîchir la vue principale
                    }
                } catch (IllegalArgumentException e) {
                    // Afficher l'erreur en cas d'ID null
                    JOptionPane.showMessageDialog(dialog, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (PersistenceException e) {
                    // Gestion des erreurs de contrainte d'unicité
                    String message = e.getMessage().toLowerCase();
                    if (message.contains("unique") || message.contains("duplicate")) {
                        JOptionPane.showMessageDialog(dialog, "Cet nom existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    e.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(dialog, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si l'email est vide, afficher une erreur
                JOptionPane.showMessageDialog(dialog, "Le nom ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Action pour le bouton "Annuler"
        cancelButton.addActionListener(event -> dialog.dispose());

        dialog.setVisible(true);
    }

// Méthode pour actualiser la page mère
    private void refreshMainFrameName() {
        SwingUtilities.getWindowAncestor(name).repaint();
    }

    //    ---------------------------------------------------------------------
//    ---------------------------------------------------------------------
//   Description :
//    ---------------------------------------------------------------------
//    ---------------------------------------------------------------------
    private void showDescriptionEditDialog() {
        JDialog dialog = new JDialog((Frame) null, "Modifier la description", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new BorderLayout(10, 10)); // Ajoute un espacement entre les panels
        dialog.setLocationRelativeTo(null);

        // Panel pour afficher "Nom Actuel" centré en haut
        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel("Description actuel : " + description_course);
        label.setFont(new java.awt.Font("sansserif", Font.BOLD, 20));
        label.setForeground(new java.awt.Color(0, 0, 204));
        titlePanel.add(label);

        // Panel pour "Nouveau Nom : " + JTextField (alignés horizontalement)
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Nouvelle description : ");
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
            String newDesc = nameField.getText().trim();

            if (newDesc.length() > 250) {
                javax.swing.JOptionPane.showMessageDialog(this, "La description du cours ne peut pas dépasser 250 caractères.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newDesc.isEmpty()) {
                // Créer un objet Students_Entity et mettre à jour l'ID et l'email
                Course_Entity cou = new Course_Entity();
                cou.setId(Long.valueOf(id)); // Remplacer 'id' par l'identifiant de l'étudiant
                cou.setDescription(newDesc); // Mettre à jour l'email

                try {
                    // Tenter la mise à jour de l'e-mail via le contrôleur
                    boolean success = controller.updateDescriptionCourse(cou);

                    if (success) {
                        // Mise à jour réussie uniquement si l'e-mail n'existe pas déjà
                        JOptionPane.showMessageDialog(dialog, "Description modifié avec succès !");
                        name.setText("2- Description : " + newDesc); // Mise à jour de l'affichage du label
                        dialog.dispose(); // Fermer la fenêtre
                        refreshMainFrameDescription(); // Rafraîchir la vue principale
                    }
                } catch (IllegalArgumentException e) {
                    // Afficher l'erreur en cas d'ID null
                    JOptionPane.showMessageDialog(dialog, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (PersistenceException e) {
                    // Gestion des erreurs de contrainte d'unicité
                    String message = e.getMessage().toLowerCase();
                    if (message.contains("unique") || message.contains("duplicate")) {
                        JOptionPane.showMessageDialog(dialog, "Cette description existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    e.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(dialog, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si l'email est vide, afficher une erreur
                JOptionPane.showMessageDialog(dialog, "La description ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Action pour le bouton "Annuler"
        cancelButton.addActionListener(event -> dialog.dispose());

        dialog.setVisible(true);
    }

// Méthode pour actualiser la page mère
    private void refreshMainFrameDescription() {
        SwingUtilities.getWindowAncestor(description).repaint();
    }

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
        name = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Org/Images/salle-de-cours(3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel1)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Élément à modfier");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(51, 102, 255)));

        name.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        name.setForeground(new java.awt.Color(0, 0, 204));
        name.setText("1- Nom :");
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (name != null) {
                    showNameEditDialog();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                name.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                name.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                name.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                name.setForeground(new java.awt.Color(0, 0, 204));

            }
        });

        description.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        description.setForeground(new java.awt.Color(0, 0, 204));
        description.setText("2- Description :");
        description.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (description != null) {
                    showDescriptionEditDialog();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e) {
                description.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                description.setForeground(new java.awt.Color(0, 0, 0));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                description.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                description.setForeground(new java.awt.Color(0, 0, 204));
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
                            .addComponent(description)
                            .addComponent(name))
                        .addGap(0, 276, Short.MAX_VALUE))))
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
                .addComponent(name)
                .addGap(18, 18, 18)
                .addComponent(description)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Sm_Ctrl ctx = new Sm_Ctrl();
        dispose();
        Dashboard_All_Course AllCourseFrame = new Dashboard_All_Course(ctx);
        AllCourseFrame.setLocationRelativeTo(null);
        AllCourseFrame.setVisible(true);
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
            java.util.logging.Logger.getLogger(Updates_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Updates_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Updates_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Updates_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sm_Ctrl xt = new Sm_Ctrl();
                new Updates_Course("", "", "", xt).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel description;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private static javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables
}
