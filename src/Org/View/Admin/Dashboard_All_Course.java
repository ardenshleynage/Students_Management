/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Org.View.Admin;

import Org.Ctrl.Sm_Ctrl;
import Org.Entity.Class_Entity;
import Org.Entity.Course_Entity;
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
public class Dashboard_All_Course extends javax.swing.JFrame {

    private final Sm_Ctrl controller;

    /**
     * Creates new form Dashboard_All_Course
     *
     * @param controller
     */
    public Dashboard_All_Course(Sm_Ctrl controller) {
        this.controller = controller;
        initComponents();

        Dash dashPanel = new Dash();
        jPanel1.setLayout(new java.awt.BorderLayout()); // Remplir tout le panel
        jPanel1.add(dashPanel, BorderLayout.CENTER);

        // Modifier les couleurs en fonction de la JFrame
        dashPanel.changerCouleurLabel4(Color.WHITE); // Rouge par exemple

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
        populateTable();
        ClickStudents();
    }

    public void populateTable() {
        boolean NoCourse = controller.NoCourse();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        if (NoCourse) {
            String emptyMessage = "Il n'y a pas de cours, vérifier la corbeille.";
            model.setColumnCount(1);// Réduire à une seule colonne
            model.setColumnIdentifiers(new String[]{""}); // Supprimer les en-têtes
            model.addRow(new Object[]{emptyMessage}); // Ajouter une ligne avec le message

            // Ajuster l'affichage du tableau
            jTable2.setModel(model);
            jTable2.setEnabled(false);// Ajoutez une ligne avec le message
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            jTable2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        } else {
            List<Course_Entity> CourseList = controller.getAllCourse(); // Fetch all people from the controller

            // Clear the existing rows
            int rowNumber = 1;

            for (Course_Entity course : CourseList) {

                String statut = course.getAccess() == 1 ? "Autoriser" : course.getAccess() == 2 ? "Bloquer" : "Inconnu";

                Object[] rowData = {
                    rowNumber++,
                    course.getId(),
                    course.getName(),
                    course.getDescription(),
                    statut,
                    course.getReg_date(),};

                model.addRow(rowData);
            }
            jTable2.getColumnModel().getColumn(1).setMinWidth(0);
            jTable2.getColumnModel().getColumn(1).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(1).setWidth(0);

        }

        // Adjust the table's total height based on the number of rows
    }

    private void ClickStudents() {
        jTable2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable2.getSelectedRow();
                if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(e)) {
                    try {
                        List<Course_Entity> Courses = controller.getAllCourse();
                        Course_Entity course = Courses.get(selectedRow);

                        if (course != null) {
                            String number = String.valueOf(course.getId());
                            String name = course.getName();
                            String description = course.getDescription();
                            int statuts = course.getAccess();
                            LocalDate regDate = course.getReg_date();

                            String statut = (statuts == 1) ? "Autorisé" : (statuts == 2) ? "Bloqué" : "Inconnu";
                            Teachers_Entity teacher = null;
                            // Vous pouvez récupérer les classes liées à ce cours
                            for (Class_Entity classEntity : course.getClasses()) {  // Assurez-vous d'avoir une méthode getClassEntities() dans Course_Entity
                                if (classEntity.getTeacher() != null) {
                                    teacher = classEntity.getTeacher();
                                    break;  // Vous pouvez sortir dès que vous trouvez le premier enseignant
                                }
                            }

                            String teacherName = "Inconnu"; // Valeur par défaut si aucun enseignant n'est trouvé
                            if (teacher != null) {
                                teacherName = teacher.getFirst_name() + " " + teacher.getLast_name();  // Utiliser le prénom et le nom du professeur
                            }

                            // Construire le message avec le nom de l'enseignant
                            String message = String.format(
                                    "Nom : %s\nDescription : %s\nStatut : %s\nDate d'enregistrement : %s\nProfesseur : %s\n",
                                    name, description, statut, regDate, teacherName
                            );

                            // Définir les options dynamiques en fonction du statut
                            Object[] options = (statuts == 1)
                                    ? new Object[]{"Bloquer", "Modifier", "Supprimer", "Afficher les étudiants", "Annuler"}
                                    : new Object[]{"Autoriser", "Modifier", "Supprimer", "Afficher les étudiants", "Annuler"};

                            int option = JOptionPane.showOptionDialog(
                                    Dashboard_All_Course.this,
                                    message,
                                    "Détails du cours",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null,
                                    options,
                                    "Annuler"
                            );

                            // Actions basées sur l'option sélectionnée
                            switch (option) {
                                case 0:
                                    if (statuts == 1) {
                                        controller.BlockCourse(course);
                                        JOptionPane.showMessageDialog(Dashboard_All_Course.this, "Cours bloqué avec succès !");
                                        populateTable();
                                    } else {
                                        controller.AllowCourse(course);
                                        JOptionPane.showMessageDialog(Dashboard_All_Course.this, "Cours autorisé avec succès !");
                                        populateTable();
                                    }

                                    break;

                                case 1:
                                    dispose();

                                    Sm_Ctrl cx = new Sm_Ctrl();
                                    Updates_Course UpdateFrame = new Updates_Course(number, name, description, cx);
                                    UpdateFrame.setLocationRelativeTo(null);
                                    UpdateFrame.setVisible(true);

                                    break;

                                case 2:
                                    DeleteCourseQuestion(course);
                                    break;

                                case 3:
                                    StudentsListDialog.showDialog(Dashboard_All_Course.this, course.getId(), controller);
                                    break;
                                case 4:
                                    break;

                            }
                        } else {
                            JOptionPane.showMessageDialog(Dashboard_All_Course.this, "Erreur : le cours sélectionné n'a pas pu être trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Dashboard_All_Course.this, "Erreur lors de la récupération des données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });
    }

    private void DeleteCourseQuestion(Course_Entity course) {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement supprimez le cours ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Non"
        );

        // Handle user selection
        if (option == JOptionPane.YES_OPTION) {
            handleDeleteCourse(course);
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

    private void handleDeleteCourse(Course_Entity course) {
        boolean success = controller.SureDeleteCourse(course);

        if (success) {
            JOptionPane.showMessageDialog(this, "Le cours a été supprimés.", "Information", JOptionPane.INFORMATION_MESSAGE);
            populateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion du cours.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSearchDialog() {
        JDialog dialog = new JDialog((Frame) null, "Recherche par nom", true);
        dialog.setSize(400, 150);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setLocationRelativeTo(null);

        // Panel pour l'entrée utilisateur
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label2 = new JLabel("Entrer le nom: ");
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
            String name = nameField.getText().trim();

            if (!name.isEmpty()) {
                Course_Entity course = controller.findCourseByName(name);

                if (course != null) {
                    Sm_Ctrl cy = new Sm_Ctrl();
                    dialog.dispose(); // Fermer la boîte de dialogue
                    dispose(); // Fermer la fenêtre principale

                    // Ouvrir la nouvelle fenêtre avec les résultats
                    ShowResultSearchCourse searchFrame = new ShowResultSearchCourse(cy, course);
                    searchFrame.setLocationRelativeTo(null);
                    searchFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(dialog,
                            "Aucun nom trouvé pour la recherche : " + name,
                            "Résultat introuvable",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Le nom ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
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

        jLabel5.setBackground(new java.awt.Color(51, 102, 255));
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
                Dashboard_All_Course AllcourseFrame = new Dashboard_All_Course(contrx);
                AllcourseFrame.setLocationRelativeTo(null);
                AllcourseFrame.setVisible(true);

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
                jLabel5.setBackground(new java.awt.Color(51,102,255));
                jLabel5.setIcon(normalIcon);

            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
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
                jLabel11.setBackground(new java.awt.Color(255, 255, 255));
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
                Dashboard_Trash_Course TrashcourseFrame = new Dashboard_Trash_Course(contry);
                TrashcourseFrame.setLocationRelativeTo(null);
                TrashcourseFrame.setVisible(true);

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
                "#", "id", "Nom", "Description", "Statut", "Date d'enregistrement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            java.util.logging.Logger.getLogger(Dashboard_All_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_All_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_All_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_All_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sm_Ctrl controllers = new Sm_Ctrl();
                new Dashboard_All_Course(controllers).setVisible(true);
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
