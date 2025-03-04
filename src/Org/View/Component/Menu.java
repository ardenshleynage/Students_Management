package Org.View.Component;

import Org.Ctrl.Sm_Ctrl;
import Org.View.Admin.Add_Course;
import Org.View.Admin.Add_Students;
import Org.View.Admin.Add_Teachers;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Menu extends javax.swing.JPanel {

    public Menu() {
        initComponents();

    }

    public void MenuLabel(java.awt.Color couleur) {
        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel1.setForeground(new java.awt.Color(0, 0, 0));
            }
        });

        jLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel2.setForeground(new java.awt.Color(0, 0, 0));
            }
        });

        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                jLabel3.setForeground(new java.awt.Color(0, 0, 0));
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ajouter étudiants");
        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl controllers = new Sm_Ctrl();
                Add_Students AsFrame = new Add_Students(controllers);
                AsFrame.setLocationRelativeTo(null);
                AsFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Menu.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

        });

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ajouter enseignants");
        jLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl controllerx = new Sm_Ctrl();
                Add_Teachers TFrame = new Add_Teachers(controllerx);
                TFrame.setLocationRelativeTo(null);
                TFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Menu.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

        });

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Ajouter cours");
        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sm_Ctrl controllerf = new Sm_Ctrl();
                Add_Course CFrame = new Add_Course(controllerf);
                CFrame.setLocationRelativeTo(null);
                CFrame.setVisible(true);

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(Menu.this);
                if (topFrame != null) {
                    topFrame.dispose();
                }

            }

        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        area.add(new Area(new Rectangle2D.Double(0, 20, getWidth(), getHeight())));
        g2.fill(area);
        g2.dispose();
        super.paint(grphcs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
