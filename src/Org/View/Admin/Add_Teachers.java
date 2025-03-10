/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Org.View.Admin;

import Org.Ctrl.Sm_Ctrl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author an
 */
public class Add_Teachers extends javax.swing.JFrame {

    private Integer sex = 0;
    private final Sm_Ctrl controller;

    /**
     * Creates new form Add_Teachers
     *
     * @param controller
     */
    public Add_Teachers(Sm_Ctrl controller) {
        this.controller = controller;
        initComponents();
    }

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
            throw new RuntimeException("Erreur de hachage : " + e.getMessage());
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        l_name_field = new javax.swing.JTextField();
        f_name_field = new javax.swing.JTextField();
        view_pass = new javax.swing.JCheckBox();
        password_field = new javax.swing.JPasswordField();
        date = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        male = new javax.swing.JCheckBox();
        female = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        email_field = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        phone_field = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        speciality_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Org/Images/professeur(4).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Ajoutez Enseignant");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(51, 102, 255)));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Nom :");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Prénom :");

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("Mots de passe :");

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

        l_name_field.setBackground(new java.awt.Color(51, 102, 255));
        l_name_field.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        l_name_field.setForeground(new java.awt.Color(0, 0, 0));

        f_name_field.setBackground(new java.awt.Color(51, 102, 255));
        f_name_field.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        f_name_field.setForeground(new java.awt.Color(0, 0, 0));

        view_pass.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        view_pass.setForeground(new java.awt.Color(0, 0, 204));
        view_pass.setText("Montrez le mots de passe");
        view_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_passActionPerformed(evt);
            }
        });

        password_field.setBackground(new java.awt.Color(51, 102, 255));
        password_field.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        password_field.setEchoChar('*');

        date.setBackground(new java.awt.Color(51, 102, 255));
        date.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N

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

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 204));
        jLabel8.setText("Date de naissance :");

        male.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        male.setForeground(new java.awt.Color(51, 102, 255));
        male.setText("Maculin");
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });

        female.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        female.setForeground(new java.awt.Color(51, 102, 255));
        female.setText("Féminin");
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("Sexe :");

        email_field.setBackground(new java.awt.Color(51, 102, 255));
        email_field.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        email_field.setForeground(new java.awt.Color(0, 0, 0));

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 204));
        jLabel10.setText("E-mail :");

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 204));
        jLabel12.setText("Téléphone :");

        phone_field.setBackground(new java.awt.Color(51, 102, 255));
        phone_field.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        phone_field.setForeground(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 204));
        jLabel13.setText("Spécialité :");

        speciality_field.setBackground(new java.awt.Color(51, 102, 255));
        speciality_field.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        speciality_field.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(speciality_field)
                            .addComponent(password_field)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(133, 133, 133)
                        .addComponent(f_name_field))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 111, 111)
                        .addComponent(l_name_field, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(male)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(female))
                            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phone_field)
                            .addComponent(email_field)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(76, 76, 76))
                            .addComponent(view_pass, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(l_name_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(f_name_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(male)
                        .addComponent(female))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(email_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(phone_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(speciality_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(password_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6)))
                .addGap(50, 50, 50)
                .addComponent(view_pass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
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
        String l_name_input = l_name_field.getText().trim();
        String f_name_input = f_name_field.getText().trim();
        String phone_input = phone_field.getText().trim();
        String email_input = email_field.getText().trim();
        String password_input = password_field.getText().trim();
        String speciality_input = speciality_field.getText().trim();
        String dobStr = date.getDateFormatString();

        if (l_name_input.isEmpty() || f_name_input.isEmpty() || phone_input.isEmpty() || email_input.isEmpty() || password_input.isEmpty() || speciality_input.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (l_name_input.split("\\s+").length > 2) {
            javax.swing.JOptionPane.showMessageDialog(this, "Le nom ne peut contenir que deux noms composés.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        String regex = "^[A-Za-z-]+$";

        String[] words = l_name_input.split("\\s+");

        for (String word : words) {

            if (!word.matches(regex)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Le nom ne peut contenir que des lettres et des tirets.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (sex == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Veuillez sélectionner un sexe.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        java.util.Date selectedDate = date.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une date.");
            return;
        }

        LocalDate dob = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (f_name_input.split("\\s+").length > 3) {
            javax.swing.JOptionPane.showMessageDialog(this, "Le prénom ne peut contenir que trois prénoms composés.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        String regexf = "^[A-Za-z-]+$";

        String[] wordsf = f_name_input.split("\\s+");

        for (String fname : wordsf) {
            if (!fname.matches(regexf)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Le prénom ne peut contenir que des lettres et des tirets.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (password_input.length() < 4) {
            javax.swing.JOptionPane.showMessageDialog(this, "Le mots de passe doit contenir au moins 4 charactères.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            password_field.setText("");
            return;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email_input)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Adresse email invalide.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        int phone;
        if (!phone_input.matches("\\d{8}")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Le numéro de téléphone doit contenir 8 chiffres.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            phone = Integer.parseInt(phone_input);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Numéro de téléphone invalide.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hashedPassword = hashPassword(password_input);

        try {
            boolean success = controller.addTeachers(l_name_input, f_name_input, dob, email_input, phone, sex, speciality_input, LocalDate.now(), hashedPassword, 1);

            if (success) {
                JOptionPane.showMessageDialog(this, "Enseignant ajouté avec succès !");
                Sm_Ctrl contr = new Sm_Ctrl();
                this.dispose();
                Add_Teachers AsFrame = new Add_Teachers(contr);
                AsFrame.setLocationRelativeTo(null);
                AsFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout !");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void view_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_passActionPerformed
        // TODO add your handling code here:
        if (view_pass.isSelected()) {
            password_field.setEchoChar((char) 0);
        } else {
            password_field.setEchoChar('*');
        }
    }//GEN-LAST:event_view_passActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
        Dashboard_Home DhFrame = new Dashboard_Home();
        DhFrame.setLocationRelativeTo(null);
        DhFrame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed
        // TODO add your handling code here:
        sex = 1;
        female.setSelected(false);
    }//GEN-LAST:event_maleActionPerformed

    private void femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleActionPerformed
        // TODO add your handling code here:
        sex = 2;
        male.setSelected(false);
    }//GEN-LAST:event_femaleActionPerformed

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
            java.util.logging.Logger.getLogger(Add_Teachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Teachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Teachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Teachers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sm_Ctrl controllers = new Sm_Ctrl();
                new Add_Teachers(controllers).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField email_field;
    private javax.swing.JTextField f_name_field;
    private javax.swing.JCheckBox female;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField l_name_field;
    private javax.swing.JCheckBox male;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JTextField phone_field;
    private javax.swing.JTextField speciality_field;
    private javax.swing.JCheckBox view_pass;
    // End of variables declaration//GEN-END:variables
}
