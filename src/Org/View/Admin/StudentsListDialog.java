/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Org.View.Admin;

import Org.Ctrl.Sm_Ctrl;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author an
 */
public class StudentsListDialog extends JDialog {

    public StudentsListDialog(Frame parent, Long courseId, Sm_Ctrl controller) {
        super(parent, "Étudiants inscrits", true);
        setSize(500, 300);
        setLocationRelativeTo(parent);

        // Récupérer les étudiants inscrits
        List<Object[]> students = controller.getStudentsByCourse(courseId);

        // Création du tableau
        String[] columnNames = {"Prénom", "Nom"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Object[] student : students) {
            // Ajoute uniquement prénom et nom (indices 1 et 2)
            Object[] row = {student[1], student[2]};
            model.addRow(row);
        }

        

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Ajout au contenu
        add(scrollPane, BorderLayout.CENTER);

        // Bouton fermer
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);
    }

    public static void showDialog(Frame parent, Long courseId, Sm_Ctrl controller) {
        new StudentsListDialog(parent, courseId, controller).setVisible(true);
    }
}
