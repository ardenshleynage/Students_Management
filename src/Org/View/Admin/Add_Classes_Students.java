/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Org.View.Admin;

import Org.Ctrl.Sm_Ctrl;
import Org.Entity.Course_Entity;
import Org.Entity.Students_Entity;
import Org.Entity.Teachers_Entity;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author an
 */
public class Add_Classes_Students extends javax.swing.JFrame {

    private final Sm_Ctrl controller;
    private final String id;
    private final String lname;
    private final String fname;
    private final String dob;

    /**
     * Creates new form Add_Classes_Students
     *
     * @param id
     * @param lname
     * @param fname
     * @param dob
     * @param controller
     */
    public Add_Classes_Students(String id, String lname, String fname, String dob, Sm_Ctrl controller) {
        this.controller = controller;
        initComponents();
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
        loadCourses();
        updateDetails(lname, fname, dob);
    }

    public static void updateDetails(String lname, String fname, String dob) {
        if (l_name != null) {
            l_name.setText("Nom : " + lname);
        }
        if (f_name != null) {
            f_name.setText("Prénom : " + fname);
        }

        if (d_o_b != null) {
            LocalDate birthDate = LocalDate.parse(dob); // Assure-toi que `dob` est bien au format "YYYY-MM-DD"
            LocalDate currentDate = LocalDate.now();

            int age = Period.between(birthDate, currentDate).getYears();
            d_o_b.setText("Age: " + age + " ans");
        }

    }

    private void loadCourses() {
        try {
            Sm_Ctrl courseCtrl = new Sm_Ctrl();
            jComboBox1.removeAllItems();
            // Vérifier que l'ID est bien un nombre valide avant de le convertir
            if (id == null || id.trim().isEmpty()) {
                System.out.println("ID de l'étudiant invalide.");
                return;
            }

            Long studentId = Long.valueOf(id);
            Students_Entity student = courseCtrl.getStudentById(studentId);

            // Vérifier que l'étudiant existe avant de poursuivre
            if (student == null) {
                System.out.println("Étudiant introuvable.");
                return;
            }

            List<Course_Entity> courses = courseCtrl.getUnassignedCoursesForStudent(student);

            // Vérifier si aucun cours disponible
            if (courses == null || courses.isEmpty()) {
                jComboBox1.addItem("Aucun cours disponible");

                return;
            }

            // Nettoyer et remplir le JComboBox avec les cours non assignés
            for (Course_Entity course : courses) {
                jComboBox1.addItem(course.getName()); // Ajoute chaque cours par son nom
            }

        } catch (NumberFormatException e) {
            System.out.println("Erreur : ID invalide.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors du chargement des cours.");
            e.printStackTrace();
        }
    }

    private String getSelectedCourse() {
        Object selectedItem = jComboBox1.getSelectedItem();

        if (selectedItem != null) {
            return selectedItem.toString(); // Retourne le nom du cours sélectionné
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un cours.", "Avertissement", JOptionPane.WARNING_MESSAGE);
            return null; // Retourne null si aucune sélection n'est faite
        }
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        l_name = new javax.swing.JLabel();
        f_name = new javax.swing.JLabel();
        d_o_b = new javax.swing.JLabel();

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
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel1)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Choisisez un Cours");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(51, 102, 255)));

        jButton1.setBackground(new java.awt.Color(51, 102, 255));
        jButton1.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Envoyer");
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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        jComboBox1.setBackground(new java.awt.Color(51, 102, 255));
        jComboBox1.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        l_name.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        l_name.setForeground(new java.awt.Color(0, 0, 204));
        l_name.setText("Nom :");

        f_name.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        f_name.setForeground(new java.awt.Color(0, 0, 204));
        f_name.setText("Prénom :");

        d_o_b.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        d_o_b.setForeground(new java.awt.Color(0, 0, 204));
        d_o_b.setText("Age :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(l_name)
                                .addComponent(f_name)
                                .addComponent(d_o_b)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(173, 173, 173))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(l_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(f_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d_o_b)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String selectedCourse = getSelectedCourse();

        if (selectedCourse != null) {
            System.out.println("Cours sélectionné : " + selectedCourse);

            try {
                Long studentId = Long.valueOf(id);
                Sm_Ctrl controller = new Sm_Ctrl();

                boolean success = controller.inscrireEtudiant(studentId, selectedCourse);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Étudiant inscrit au cours avec succès !");
                    loadCourses(); // Rafraîchir l'affichage des cours
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de l'inscription de l'étudiant, Vérifier qu'un enseignant est déja attribuié à un cours.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erreur : ID étudiant invalide.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un cours.");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
        Dashboard_All_Students AtFrame = new Dashboard_All_Students(controller);
        AtFrame.setLocationRelativeTo(null);
        AtFrame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Add_Classes_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Classes_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Classes_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Classes_Students.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sm_Ctrl controllers = new Sm_Ctrl();
                new Add_Classes_Students("", "", "", "", controllers).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel d_o_b;
    private static javax.swing.JLabel f_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private static javax.swing.JLabel l_name;
    // End of variables declaration//GEN-END:variables
}
