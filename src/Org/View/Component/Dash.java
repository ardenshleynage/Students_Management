/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Org.View.Component;

import Org.Ctrl.Sm_Ctrl;
import Org.View.Admin.Dashboard_All_Course;
import Org.View.Admin.Dashboard_All_Students;
import Org.View.Admin.Dashboard_All_Teachers;
import Org.View.Admin.Dashboard_Home;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author an
 */
public class Dash extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    public Dash() {
        initComponents();

    }
    // Méthode pour changer la couleur des labels

//   -----------------------------------------
//    Label 1
// ------------------------------------------
    public void changerCouleurLabel1(java.awt.Color couleur) {
        jLabel4.setBackground(couleur);
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Dashboard_Home DhFrame = new Dashboard_Home();
                DhFrame.setLocationRelativeTo(null);
                DhFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel4.setBackground(new java.awt.Color(0, 0, 0));
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel4.setBackground(new java.awt.Color(255, 255, 255));
                jLabel4.setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl cont = new Sm_Ctrl();
                Dashboard_All_Students AllstudentsFrame = new Dashboard_All_Students(cont);
                AllstudentsFrame.setLocationRelativeTo(null);
                AllstudentsFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel3.setBackground(new java.awt.Color(255, 255, 255));
                jLabel3.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel3.setBackground(new java.awt.Color(0, 0, 0));
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl conr = new Sm_Ctrl();
                Dashboard_All_Teachers AllteachersFrame = new Dashboard_All_Teachers(conr);
                AllteachersFrame.setLocationRelativeTo(null);
                AllteachersFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame
                        != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel5.setBackground(new java.awt.Color(255, 255, 255));
                jLabel5.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel5.setBackground(new java.awt.Color(0, 0, 0));
                jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl contrwe = new Sm_Ctrl();
                Dashboard_All_Course AllcourseFrame = new Dashboard_All_Course(contrwe);
                AllcourseFrame.setLocationRelativeTo(null);
                AllcourseFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel7.setBackground(new java.awt.Color(255, 255, 255));
                jLabel7.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel7.setBackground(new java.awt.Color(0, 0, 0));
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel8.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                LogoutQuestion();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel8.setBackground(new java.awt.Color(255, 255, 255));
                jLabel8.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel8.setBackground(new java.awt.Color(0, 0, 0));
                jLabel8.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        // Assurez-vous que les labels restent opaques
        jLabel4.setOpaque(true);
        jLabel3.setOpaque(true);
        jLabel5.setOpaque(true);
        jLabel7.setOpaque(true);
        jLabel8.setOpaque(true);
    }
//   -----------------------------------------
//    Label 2
// ------------------------------------------

    public void changerCouleurLabel2(java.awt.Color couleur) {
        jLabel3.setBackground(couleur);
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl cont = new Sm_Ctrl();
                Dashboard_All_Students AllstudentsFrame = new Dashboard_All_Students(cont);
                AllstudentsFrame.setLocationRelativeTo(null);
                AllstudentsFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel3.setBackground(new java.awt.Color(0, 0, 0));
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel3.setBackground(new java.awt.Color(255, 255, 255));
                jLabel3.setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Dashboard_Home DhFrame = new Dashboard_Home();
                DhFrame.setLocationRelativeTo(null);
                DhFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel4.setBackground(new java.awt.Color(255, 255, 255));
                jLabel4.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel4.setBackground(new java.awt.Color(0, 0, 0));
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl conr = new Sm_Ctrl();
                Dashboard_All_Teachers AllteachersFrame = new Dashboard_All_Teachers(conr);
                AllteachersFrame.setLocationRelativeTo(null);
                AllteachersFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame
                        != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel5.setBackground(new java.awt.Color(255, 255, 255));
                jLabel5.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel5.setBackground(new java.awt.Color(0, 0, 0));
                jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl contrwe = new Sm_Ctrl();
                Dashboard_All_Course AllcourseFrame = new Dashboard_All_Course(contrwe);
                AllcourseFrame.setLocationRelativeTo(null);
                AllcourseFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel7.setBackground(new java.awt.Color(255, 255, 255));
                jLabel7.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel7.setBackground(new java.awt.Color(0, 0, 0));
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LogoutQuestion();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel8.setBackground(new java.awt.Color(255, 255, 255));
                jLabel8.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel8.setBackground(new java.awt.Color(0, 0, 0));
                jLabel8.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        // Assurez-vous que les labels restent opaques
        jLabel4.setOpaque(true);
        jLabel3.setOpaque(true);
        jLabel5.setOpaque(true);
        jLabel7.setOpaque(true);
        jLabel8.setOpaque(true);
    }

    //   -----------------------------------------
//    Label 3
// ------------------------------------------
    public void changerCouleurLabel3(java.awt.Color couleur) {
        jLabel5.setBackground(couleur);
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl conr = new Sm_Ctrl();
                Dashboard_All_Teachers AllteachersFrame = new Dashboard_All_Teachers(conr);
                AllteachersFrame.setLocationRelativeTo(null);
                AllteachersFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel5.setBackground(new java.awt.Color(0, 0, 0));
                jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel5.setBackground(new java.awt.Color(255, 255, 255));
                jLabel5.setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Dashboard_Home DhFrame = new Dashboard_Home();
                DhFrame.setLocationRelativeTo(null);
                DhFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel4.setBackground(new java.awt.Color(255, 255, 255));
                jLabel4.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel4.setBackground(new java.awt.Color(0, 0, 0));
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl cont = new Sm_Ctrl();
                Dashboard_All_Students AllstudentsFrame = new Dashboard_All_Students(cont);
                AllstudentsFrame.setLocationRelativeTo(null);
                AllstudentsFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel3.setBackground(new java.awt.Color(255, 255, 255));
                jLabel3.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel3.setBackground(new java.awt.Color(0, 0, 0));
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl contrwe = new Sm_Ctrl();
                Dashboard_All_Course AllcourseFrame = new Dashboard_All_Course(contrwe);
                AllcourseFrame.setLocationRelativeTo(null);
                AllcourseFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel7.setBackground(new java.awt.Color(255, 255, 255));
                jLabel7.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel7.setBackground(new java.awt.Color(0, 0, 0));
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LogoutQuestion();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel8.setBackground(new java.awt.Color(255, 255, 255));
                jLabel8.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel8.setBackground(new java.awt.Color(0, 0, 0));
                jLabel8.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        // Assurez-vous que les labels restent opaques
        jLabel4.setOpaque(true);
        jLabel3.setOpaque(true);
        jLabel5.setOpaque(true);
        jLabel7.setOpaque(true);
        jLabel8.setOpaque(true);
    }
//   -----------------------------------------
//    Label 4
// ------------------------------------------

    public void changerCouleurLabel4(java.awt.Color couleur) {
        jLabel7.setBackground(couleur);
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl contrwe = new Sm_Ctrl();
                Dashboard_All_Course AllcourseFrame = new Dashboard_All_Course(contrwe);
                AllcourseFrame.setLocationRelativeTo(null);
                AllcourseFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel7.setBackground(new java.awt.Color(0, 0, 0));
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel7.setBackground(new java.awt.Color(255, 255, 255));
                jLabel7.setForeground(new java.awt.Color(0, 0, 0));
            }
        });
        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Dashboard_Home DhFrame = new Dashboard_Home();
                DhFrame.setLocationRelativeTo(null);
                DhFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel4.setBackground(new java.awt.Color(255, 255, 255));
                jLabel4.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel4.setBackground(new java.awt.Color(0, 0, 0));
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl cont = new Sm_Ctrl();
                Dashboard_All_Students AllstudentsFrame = new Dashboard_All_Students(cont);
                AllstudentsFrame.setLocationRelativeTo(null);
                AllstudentsFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel3.setBackground(new java.awt.Color(255, 255, 255));
                jLabel3.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel3.setBackground(new java.awt.Color(0, 0, 0));
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl conr = new Sm_Ctrl();
                Dashboard_All_Teachers AllteachersFrame = new Dashboard_All_Teachers(conr);
                AllteachersFrame.setLocationRelativeTo(null);
                AllteachersFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

            public void mouseEntered(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel5.setBackground(new java.awt.Color(255, 255, 255));
                jLabel5.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel5.setBackground(new java.awt.Color(0, 0, 0));
                jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        jLabel8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LogoutQuestion();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel8.setBackground(new java.awt.Color(255, 255, 255));
                jLabel8.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel8.setBackground(new java.awt.Color(0, 0, 0));
                jLabel8.setForeground(new java.awt.Color(255, 255, 255));
            }
        });

        // Assurez-vous que les labels restent opaques
        jLabel4.setOpaque(true);
        jLabel3.setOpaque(true);
        jLabel5.setOpaque(true);
        jLabel7.setOpaque(true);
        jLabel8.setOpaque(true);
    }

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

        JOptionPane.showMessageDialog(null, "Vous êtes maintenant déconnecté. À bientôt !");
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Dash.this);
        if (topFrame != null) {
            topFrame.dispose();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(172, 546));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Étudiant");
        jLabel3.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Enseignant");
        jLabel5.setOpaque(true);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Cours");
        jLabel7.setOpaque(true);

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Déconnexion");
        jLabel8.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Accueil");
        jLabel4.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
