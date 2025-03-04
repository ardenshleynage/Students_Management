/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Org.View.Teachers;

import Org.Ctrl.Sm_Ctrl;
import Org.Entity.Course_Entity;
import Org.Entity.Teachers_Entity;
import Org.View.Component.Dash_Teachers;
import Org.View.Component.Panel_Teachers;
import Org.View.Home.Choice;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author an
 */
public class Dash_Teachers_Course extends javax.swing.JFrame {

    private final Sm_Ctrl controller;
    private final String id_prof;

    /**
     * Creates new form Dash_Teachers_Course
     *
     * @param id_prof
     * @param controller
     */
    public Dash_Teachers_Course(String id_prof, Sm_Ctrl controller) {
        this.controller = controller;
        initComponents();
        this.id_prof = id_prof;
        Long id_teachers = Long.valueOf(id_prof);
        populateTable(id_teachers);
        ClickStudents();
    }

    public void populateTable(Long teacherId) {
        List<String> courseNames = controller.getCourseNamesByTeacherId(teacherId);
        List<String> courseId = controller.getCourseIdByTeacherId(teacherId);

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0); // Effacer les anciennes données

        if (courseNames.isEmpty()) {
            // Aucun cours trouvé
            String emptyMessage = "Aucun cours associé à ce professeur.";
            model.setColumnCount(1); // Une seule colonne
            model.setColumnIdentifiers(new String[]{""}); // Supprime les en-têtes
            model.addRow(new Object[]{emptyMessage}); // Ajoute un message

            // Ajustement de l'affichage
            jTable2.setModel(model);
            jTable2.setEnabled(false);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            jTable2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        } else {
            // Cours trouvés
            model.setColumnCount(2); // Deux colonnes : ID et Nom
            model.setColumnIdentifiers(new String[]{"#", "Nom (cours)"});

            int rowNumber = 1;
            for (String courseName : courseNames) {
                Object[] rowData = {
                    rowNumber++, // ID du cours fictif
                    courseName // Nom du cours
                };
                model.addRow(rowData);
            }

        }
    }

    private void ClickStudents() {
        jTable2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable2.getSelectedRow();
                if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(e)) {
                    try {
                        // On récupère l'ID de l'enseignant depuis le constructeur (supposons que teacherId est déjà disponible)
                        Long id_teachers = Long.valueOf(id_prof);
                        Long teacherId = id_teachers;  // Ici, `this.teacherId` est l'ID de l'enseignant.

                        // Récupère les cours associés à cet enseignant
                        List<String> courseNames = controller.getCourseNamesByTeacherId(teacherId);
                        List<String> courseIds = controller.getCourseIdByTeacherId(teacherId);

                        // Vérifie si des cours sont associés et si l'index est valide
                        if (!courseNames.isEmpty() && selectedRow < courseNames.size()) {
                            // On récupère le cours sélectionné en fonction de la ligne sélectionnée
                            String selectedCourseName = courseNames.get(selectedRow);
                            String selectedCourseId = courseIds.get(selectedRow);
                            Long selectedCourse_Id = controller.getCourseIdByNameAndTeacher(selectedCourseName, teacherId);

                            // Affiche un message avec le nom du cours et son ID
                            String message = String.format("Nom du cours : %s", selectedCourseName, selectedCourseId);
                            int option = JOptionPane.showOptionDialog(Dash_Teachers_Course.this,
                                    message + "\nQue voulez-vous faire ?",
                                    "Détails du cours",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                    null, new String[]{"Attribuer des notes", "Annuler"}, "Attribuer des notes");

                            // Si l'utilisateur clique sur "Attribuer des notes"
                            if (option == 0) {
                                // Ajoutez ici la logique pour attribuer des notes
                                String course_id = String.valueOf(selectedCourse_Id);
                                String teachers_id = String.valueOf(id_teachers);
                                Sm_Ctrl cts = new Sm_Ctrl();
                                dispose();
                                Dash_Teachers_My_Students SFrame = new Dash_Teachers_My_Students(course_id, teachers_id, cts);
                                SFrame.setLocationRelativeTo(null);
                                SFrame.setVisible(true);
                            } // Si l'utilisateur clique sur "Annuler"
                            else if (option == 1) {
                                // Logique pour annuler (par exemple, fermer ou ne rien faire)

                            }
                        } else {
                            JOptionPane.showMessageDialog(Dash_Teachers_Course.this, "Aucun cours trouvé pour cet enseignant.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Dash_Teachers_Course.this, "Erreur lors de la récupération des données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setBackground(new java.awt.Color(51, 102, 255));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "id", "Nom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Long.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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

        jButton1.setBackground(new java.awt.Color(51, 102, 255));
        jButton1.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Déconnexion");
        jButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jButton1.setBackground(new java.awt.Color(0,0,0));
                jButton1.setForeground(new java.awt.Color(51, 102, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jButton1.setBackground(new java.awt.Color(51, 102, 255));
                jButton1.setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        jButton1.setPreferredSize(new java.awt.Dimension(89, 33));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Mes Cours");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 4, 0, new java.awt.Color(51, 102, 255)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(302, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(220, 220, 220))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(510, 510, 510))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        LogoutQuestion();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void LogoutQuestion() {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Êtes-vous sûr de vouloir vous déconnecter ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Non"
        );

        // Handle user selection
        if (option == JOptionPane.YES_OPTION) {
            handleLogout();
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

    private void handleLogout() {
        dispose();
        Choice ChoiceFrame = new Choice();
        ChoiceFrame.setLocationRelativeTo(null);
        ChoiceFrame.setVisible(true);
    }

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
            java.util.logging.Logger.getLogger(Dash_Teachers_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dash_Teachers_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dash_Teachers_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dash_Teachers_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sm_Ctrl controllers = new Sm_Ctrl();
                new Dash_Teachers_Course("", controllers).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
