/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Org.View.Admin;

import Org.Ctrl.Sm_Ctrl;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author an
 */
public class CourseListDialog extends JDialog {
    public static void showDialog(JFrame parent, Long teacherId, Sm_Ctrl controller) {
        List<Object[]> courses = controller.getTeachersByCourse(teacherId);
        
        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Aucun cours trouvé pour cet enseignant.");
            return;
        }

        // Créer un modèle de tableau pour afficher les cours
        String[] columnNames = { "Nom Cours"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Remplir le tableau avec les données
        for (Object[] course : courses) {
            Object[] row = {course[1]};
            model.addRow(row);
        }

        // Créer une JTable avec le modèle et ajouter dans la fenêtre pop-up
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Configurer la fenêtre pop-up
        JDialog dialog = new JDialog(parent, "Liste des Cours", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setSize(400, 300); // Ajuster la taille de la fenêtre
        dialog.setLocationRelativeTo(parent); // Centrer par rapport à la fenêtre parent
        dialog.setVisible(true);
    }
}

