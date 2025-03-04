/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Org.View.Teachers;

import Org.Ctrl.Sm_Ctrl;
import Org.Entity.Students_Entity;
import Org.View.Component.Dash_Teachers;
import Org.View.Component.Panel_Teachers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author an
 */
public class Dash_Teachers_My_Students extends javax.swing.JFrame {
    
    private final Sm_Ctrl controller;
    private final String id_course;
    private final String id_teacher;

    /**
     * Creates new form Dash_Teachers_My_Students
     *
     * @param id_course
     * @param id_teacher
     * @param controller
     */
    public Dash_Teachers_My_Students(String id_course, String id_teacher, Sm_Ctrl controller) {
        this.controller = controller;
        initComponents();
        this.id_course = id_course;
        this.id_teacher = id_teacher;
        
        Long course = Long.valueOf(id_course);
        Long prof = Long.valueOf(id_teacher);
        
        populateTable(prof, course);
        ClickStudentDetails();
        
    }
    
    public void populateTable(Long teacherId, Long courseId) {
        List<String> studentNames = controller.getStudentNamesByCourseAndTeacher(courseId, teacherId);
        List<Long> studentIds = controller.getStudentIdsByCourseAndTeacher(courseId, teacherId);
        
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0); // Effacer les anciennes données

        if (studentNames.isEmpty()) {
            // Aucun étudiant trouvé
            String emptyMessage = "Aucun étudiant associé à ce cours.";
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
            // Étudiants trouvés
            model.setColumnCount(2); // Deux colonnes : ID et Nom, Prénom
            model.setColumnIdentifiers(new String[]{"#", "Nom, Prénom"});
            
            int rowNumber = 1;
            for (int i = 0; i < studentNames.size(); i++) {
                Object[] rowData = {
                    rowNumber++, // Numéro d'affichage
                    studentNames.get(i) // Nom et prénom de l'étudiant
                };
                model.addRow(rowData);
            }
            
        }
    }
    
    private void ClickStudentDetails() {
        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable2.getSelectedRow();
                if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(e)) {
                    Long prof_c = Long.valueOf(id_teacher);
                    Long course_c = Long.valueOf(id_course);
                    try {

                        // Récupération des IDs stockés dans studentIds
                        Long studentId = controller.getStudentIdsByCourseAndTeacher(course_c, prof_c).get(selectedRow);

                        // Récupération des détails de l'étudiant
                        String studentName = controller.getStudentNamesByCourseAndTeacher(course_c, prof_c).get(selectedRow);

                        // Récupération de la note de l'étudiant (ScoreEntity)
                        Float studentScore = controller.getStudentScore(studentId, course_c);
                        String scoreText = (studentScore != null) ? String.valueOf(studentScore) : "Inconnu";

                        // Message affiché dans la boîte de dialogue
                        String message = String.format("Nom : %s\nNote : %s", studentName, scoreText);
                        int option = JOptionPane.showOptionDialog(
                                Dash_Teachers_My_Students.this,
                                message + "\nQue voulez-vous faire ?",
                                "Détails de l'étudiant",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new String[]{"Attribuer Note", "Modifier Note", "Supprimer Note", "Annuler"},
                                "Attribuer Note"
                        );

                        // Gestion des choix
                        switch (option) {
                            case 0: // Attribuer Note
                                String newScore = JOptionPane.showInputDialog("Entrez la note à attribuer :");
                                Float scoere = Float.valueOf(newScore);
                                
                                if (scoere != null) {
                                    if (scoere < 0 || scoere > 100) {
                                        JOptionPane.showMessageDialog(Dash_Teachers_My_Students.this, "Veuillez entrz une note compris entre 0 et 100 !");
                                    } else {
                                        controller.assignStudentScore(studentId, course_c, Float.parseFloat(newScore));
                                        JOptionPane.showMessageDialog(Dash_Teachers_My_Students.this, "Note attribuée avec succès !");
                                        populateTable(prof_c, course_c);
                                        
                                    }
                                    
                                }
                                break;
                            case 1: // Modifier Note
                                String updatedScore = JOptionPane.showInputDialog("Entrez la nouvelle note :");
                                Float scoerex = Float.valueOf(updatedScore);
                                if (scoerex != null) {
                                    if (scoerex < 0 || scoerex > 100) {
                                        JOptionPane.showMessageDialog(Dash_Teachers_My_Students.this, "Veuillez entrz une note compris entre 0 et 100 !");
                                    } else {
                                        controller.assignStudentScore(studentId, course_c, Float.parseFloat(updatedScore));
                                        JOptionPane.showMessageDialog(Dash_Teachers_My_Students.this, "Note modifiée !");
                                        populateTable(prof_c, course_c);
                                    }
                                    
                                }
                                break;
                            case 2: // Supprimer Note
                                int confirm = JOptionPane.showConfirmDialog(Dash_Teachers_My_Students.this, "Voulez-vous vraiment supprimer cette note ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                                if (confirm == JOptionPane.YES_OPTION) {
                                    controller.updateStudentScoreToNull(studentId, course_c);
                                    JOptionPane.showMessageDialog(Dash_Teachers_My_Students.this, "Note supprimée !");
                                    populateTable(prof_c, course_c);
                                }
                                break;
                            case 3: // Annuler
                                break;
                        }
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(Dash_Teachers_My_Students.this, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setBackground(new java.awt.Color(51, 102, 255));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "id_student", "Nom", "id_course", "id_teacher", "Pénom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Long.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.String.class
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

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Mes étudiants");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 4, 0, new java.awt.Color(51, 102, 255)));

        jButton1.setBackground(new java.awt.Color(51, 102, 255));
        jButton1.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Retour");
        jButton1.setPreferredSize(new java.awt.Dimension(89, 33));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 314, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(208, 208, 208))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(406, 406, 406))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
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
        Long prof = Long.valueOf(id_teacher);
        String id_pofw = String.valueOf(prof);
        Sm_Ctrl wer = new Sm_Ctrl();
        Dash_Teachers_Course DashFrame = new Dash_Teachers_Course(id_pofw, wer);
        DashFrame.setLocationRelativeTo(null);
        DashFrame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Dash_Teachers_My_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dash_Teachers_My_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dash_Teachers_My_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dash_Teachers_My_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sm_Ctrl controllers = new Sm_Ctrl();
                new Dash_Teachers_My_Students("", "", controllers).setVisible(true);
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
