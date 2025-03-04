/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Org.Ctrl;

import Org.Dao.Sm_DAO;

import Org.Entity.Super_Admin_Entity;
import Org.Dao.Sm_DAO;
import Org.Entity.Course_Entity;
import Org.Entity.Students_Entity;
import Org.Entity.Teachers_Entity;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

/**
 *
 * @author an
 */
public class Sm_Ctrl {

    private final Sm_DAO DAO;

    public Sm_Ctrl() {
        DAO = new Sm_DAO();
    }

//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
//                                      Admin    
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
    public boolean isAdminExist() {
        return DAO.isAdminExist();

    }

    public void addAdmin(String username, String last_name, String first_name, String password) {
        Super_Admin_Entity admin = new Super_Admin_Entity(1L, username, last_name, first_name, password);
        DAO.addAdmin(admin);
    }

    public Super_Admin_Entity Login_Admin(String username, String password) {
        Super_Admin_Entity admin = DAO.Login_Admin(username, password);
        if (admin != null) {
            return admin;
        }
        return null;
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Étudiants   
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    public boolean addStudent(String last_name, String first_name, LocalDate dob, String email, Integer phone, Integer sex, String address, LocalDate reg_date, String password, Integer access) {
        Students_Entity student = new Students_Entity();
        student.setLast_name(last_name);
        student.setFirst_name(first_name);
        student.setDob(dob);
        student.setEmail(email);
        student.setPhone(phone);
        student.setSex(sex);
        student.setAddress(address);
        student.setReg_date(reg_date);
        student.setPassword(password);
        student.setAccess(access);

        return DAO.addStudent(student);
    }

    public boolean NoStudents() {
        return DAO.NoStudents();
    }

    public boolean NoTrashStudents() {
        return DAO.NoTrashStudents();
    }

    public List<Students_Entity> getAllStudents() {
        return DAO.getAllStudents();
    }

    public List<Students_Entity> getAllSearchStudents() {
        return DAO.getAllSearchStudents();
    }

    public List<Students_Entity> getAllTrashStudents() {
        return DAO.getAllTrashStudents();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockStudents(Students_Entity stu) {
        if (stu == null) {
            throw new IllegalArgumentException("L'objet Students_Entity ne peut pas être null");
        }

        try {
            return DAO.BlockStudents(stu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllowStudents(Students_Entity stu) {
        if (stu == null) {
            throw new IllegalArgumentException("L'objet Students_Entity ne peut pas être null");
        }

        try {
            return DAO.AllowStudents(stu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean RestoreStudents(Students_Entity stu) {
        if (stu == null) {
            throw new IllegalArgumentException("L'objet Students_Entity ne peut pas être null");
        }
        try {
            return DAO.RestoreStudents(stu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteStudents(Students_Entity stu) {
        if (stu == null) {
            throw new IllegalArgumentException("L'objet Students_Entity ne peut pas être null");
        }
        try {
            return DAO.SureDeleteStudents(stu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureEraseStudents(Students_Entity stu) {
        if (stu == null) {
            throw new IllegalArgumentException("L'objet Students_Entity ne peut pas être null");
        }
        try {
            return DAO.SureEraseStudents(stu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateLastNameStudents(Students_Entity stu) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (stu != null && stu.getId() != null) {
                DAO.updateLastNameStudents(stu);
            } else {
                System.out.println("L'étudiant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateFirstNameStudents(Students_Entity stu) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (stu != null && stu.getId() != null) {
                DAO.updateFirstNameStudents(stu);
            } else {
                System.out.println("L'étudiant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateAddressStudents(Students_Entity stu) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (stu != null && stu.getId() != null) {
                DAO.updateAddressStudents(stu);
            } else {
                System.out.println("L'étudiant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateEmailStudents(Students_Entity stu) {
        try {
            if (stu == null || stu.getId() == null) {
                throw new IllegalArgumentException("L'étudiant ou son ID est null.");
            }

            // Tente de mettre à jour l'e-mail
            boolean success = DAO.updateEmailStudents(stu);

            if (!success) {
                JOptionPane.showMessageDialog(null, "Échec de la mise à jour : l'étudiant n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            return success;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenceException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                JOptionPane.showMessageDialog(null, "Cet e-mail existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erreur de base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateDobStudents(Students_Entity stu) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (stu != null && stu.getId() != null) {
                DAO.updateDobStudents(stu);
            } else {
                System.out.println("L'étudiant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateSexStudents(Students_Entity stu) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (stu != null && stu.getId() != null) {
                DAO.updateSexStudents(stu);
            } else {
                System.out.println("L'étudiant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updatePhoneStudents(Students_Entity stu) {
        try {
            if (stu == null || stu.getId() == null) {
                throw new IllegalArgumentException("L'étudiant ou son ID est null.");
            }

            // Tente de mettre à jour l'e-mail
            boolean success = DAO.updatePhoneStudents(stu);

            if (!success) {
                JOptionPane.showMessageDialog(null, "Échec de la mise à jour : l'étudiant n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            return success;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenceException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                JOptionPane.showMessageDialog(null, "Ce numéro de téléphoneexiste déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erreur de base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updatePasswordStudents(Students_Entity stu) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (stu != null && stu.getId() != null) {
                DAO.updatePasswordStudents(stu);
            } else {
                System.out.println("L'étudiant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateRegNumber(Students_Entity student) {
        return DAO.updateRegNumbStudents(student);
    }

    public String generateUniqueRegNumber() {
        return DAO.generateUniqueRegNumber();
    }

    public Students_Entity findStudentByRegNumber(String regNumber) {
        if (regNumber == null || regNumber.trim().isEmpty()) {
            return null; // Retourne null si l'entrée est invalide
        }

        // Appel à la méthode du DAO qui retourne un objet Students_Entity
        return DAO.findStudentByRegNumber(regNumber);
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Enseignants   
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    public boolean addTeachers(String last_name, String first_name, LocalDate dob, String email, Integer phone, Integer sex, String speciality, LocalDate reg_date, String password, Integer access) {
        Teachers_Entity teacher = new Teachers_Entity();
        teacher.setLast_name(last_name);
        teacher.setFirst_name(first_name);
        teacher.setDob(dob);
        teacher.setEmail(email);
        teacher.setPhone(phone);
        teacher.setSex(sex);
        teacher.setSpeciality(speciality);
        teacher.setReg_date(reg_date);
        teacher.setPassword(password);
        teacher.setAccess(access);

        return DAO.addTeachers(teacher);
    }

    public boolean NoTeachers() {
        return DAO.NoTeachers();
    }

    public List<Teachers_Entity> getAllTeachers() {
        return DAO.getAllTeachers();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockTeachers(Teachers_Entity tea) {
        if (tea == null) {
            throw new IllegalArgumentException("L'objet Teachers_Entity ne peut pas être null");
        }

        try {
            return DAO.BlockTeachers(tea);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllowTeachers(Teachers_Entity tea) {
        if (tea == null) {
            throw new IllegalArgumentException("L'objet Teachers_Entity ne peut pas être null");
        }

        try {
            return DAO.AllowTeachers(tea);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteTeachers(Teachers_Entity tea) {
        if (tea == null) {
            throw new IllegalArgumentException("L'objet Teachers_Entity ne peut pas être null");
        }
        try {
            return DAO.SureDeleteTeachers(tea);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Teachers_Entity findTeachersByRegNumber(String regNumber) {
        if (regNumber == null || regNumber.trim().isEmpty()) {
            return null; // Retourne null si l'entrée est invalide
        }

        // Appel à la méthode du DAO qui retourne un objet Students_Entity
        return DAO.findTeachersByRegNumber(regNumber);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateLastNameTeachers(Teachers_Entity tea) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (tea != null && tea.getId() != null) {
                DAO.updateLastNameTeachers(tea);
            } else {
                System.out.println("L'enseignant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateFirstNameTeachers(Teachers_Entity tea) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (tea != null && tea.getId() != null) {
                DAO.updateFirstNameTeachers(tea);
            } else {
                System.out.println("L'enseignant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateDobTeachers(Teachers_Entity tea) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (tea != null && tea.getId() != null) {
                DAO.updateDobTeachers(tea);
            } else {
                System.out.println("L'enseignant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateSexTeachers(Teachers_Entity tea) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (tea != null && tea.getId() != null) {
                DAO.updateSexTeachers(tea);
            } else {
                System.out.println("L'enseignant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateEmailTeachers(Teachers_Entity tea) {
        try {
            if (tea == null || tea.getId() == null) {
                throw new IllegalArgumentException("L'enseignant ou son ID est null.");
            }

            // Tente de mettre à jour l'e-mail
            boolean success = DAO.updateEmailTeachers(tea);

            if (!success) {
                JOptionPane.showMessageDialog(null, "Échec de la mise à jour : l'enseignant n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            return success;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenceException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                JOptionPane.showMessageDialog(null, "Cet e-mail existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erreur de base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateSpecialityTeachers(Teachers_Entity tea) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (tea != null && tea.getId() != null) {
                DAO.updateSpecialityTeachers(tea);
            } else {
                System.out.println("L'enseignant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updatePhoneTeachers(Teachers_Entity tea) {
        try {
            if (tea == null || tea.getId() == null) {
                throw new IllegalArgumentException("L'enseignat ou son ID est null.");
            }

            // Tente de mettre à jour l'e-mail
            boolean success = DAO.updatePhoneTeachers(tea);

            if (!success) {
                JOptionPane.showMessageDialog(null, "Échec de la mise à jour : l'enseignant n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            return success;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenceException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                JOptionPane.showMessageDialog(null, "Ce numéro de téléphoneexiste déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erreur de base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updatePasswordTeachers(Teachers_Entity tea) {
        try {
            // Vérifie que l'ID de l'étudiant n'est pas nul avant de procéder à la mise à jour
            if (tea != null && tea.getId() != null) {
                DAO.updatePasswordTeachers(tea);
            } else {
                System.out.println("L'enseignant ou son ID est null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateUniqueRegNumberTeachers() {
        return DAO.generateUniqueRegNumberTeachers();
    }

    public boolean updateRegNumberTeachers(Teachers_Entity teacher) {
        return DAO.updateRegNumbStudentsTeachers(teacher);
    }

    public boolean NoTrashTeachers() {
        return DAO.NoTrashTeachers();
    }

    public List<Teachers_Entity> getAllTrashTeachers() {
        return DAO.getAllTrashTeachers();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureEraseTeachers(Teachers_Entity tea) {
        if (tea == null) {
            throw new IllegalArgumentException("L'objet Teachers_Entity ne peut pas être null");
        }
        try {
            return DAO.SureEraseTeachers(tea);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean RestoreTeachers(Teachers_Entity tea) {
        if (tea == null) {
            throw new IllegalArgumentException("L'objet Teachers_Entity ne peut pas être null");
        }
        try {
            return DAO.RestoreTeachers(tea);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Teachers_Entity> getAllSearchTeachers() {
        return DAO.getAllSearchTeachers();
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Cours   
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    public boolean addCourse(String name, String description, Integer access, LocalDate reg_date) {
        Course_Entity course = new Course_Entity();
        course.setName(name);
        course.setDescription(description);
        course.setAccess(access);
        course.setReg_date(reg_date);

        return DAO.addCourse(course);
    }

    public boolean NoCourse() {
        return DAO.NoCourse();
    }

    public List<Course_Entity> getAllCourse() {
        return DAO.getAllCourse();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockCourse(Course_Entity cou) {
        if (cou == null) {
            throw new IllegalArgumentException("L'objet Course_Entity ne peut pas être null");
        }

        try {
            return DAO.BlockCourse(cou);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllowCourse(Course_Entity cou) {
        if (cou == null) {
            throw new IllegalArgumentException("L'objet Course_Entity ne peut pas être null");
        }

        try {
            return DAO.AllowCourse(cou);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteCourse(Course_Entity cou) {
        if (cou == null) {
            throw new IllegalArgumentException("L'objet Course_Entity ne peut pas être null");
        }
        try {
            return DAO.SureDeleteCourse(cou);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Course_Entity findCourseByName(String Name) {
        if (Name == null || Name.trim().isEmpty()) {
            return null; // Retourne null si l'entrée est invalide
        }

        // Appel à la méthode du DAO qui retourne un objet Students_Entity
        return DAO.findCourseByName(Name);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateNameCourse(Course_Entity cou) {
        try {
            if (cou == null || cou.getId() == null) {
                throw new IllegalArgumentException("Le cours ou son ID est null.");
            }

            // Tente de mettre à jour l'e-mail
            boolean success = DAO.updateNameCourse(cou);

            if (!success) {
                JOptionPane.showMessageDialog(null, "Échec de la mise à jour : le cours n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            return success;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenceException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                JOptionPane.showMessageDialog(null, "Ce nom existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erreur de base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateDescriptionCourse(Course_Entity cou) {
        try {
            if (cou == null || cou.getId() == null) {
                throw new IllegalArgumentException("Le cours ou son ID est null.");
            }

            // Tente de mettre à jour l'e-mail
            boolean success = DAO.updateDescriptionCourse(cou);

            if (!success) {
                JOptionPane.showMessageDialog(null, "Échec de la mise à jour : le cours n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            return success;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenceException e) {
            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                JOptionPane.showMessageDialog(null, "Cette description existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erreur de base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur inattendue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean RestoreCourse(Course_Entity cou) {
        if (cou == null) {
            throw new IllegalArgumentException("L'objet Course_Entity ne peut pas être null");
        }
        try {
            return DAO.RestoreCourse(cou);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean NoTrashCourse() {
        return DAO.NoTrashCourse();
    }

    public List<Course_Entity> getAllTrashCourse() {
        return DAO.getAllTrashCourse();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureEraseCourse(Course_Entity cou) {
        if (cou == null) {
            throw new IllegalArgumentException("L'objet Course_Entity ne peut pas être null");
        }
        try {
            return DAO.SureEraseCourse(cou);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Classes
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    public List<Course_Entity> getAllCoursesClasses() {
        return DAO.getAllCoursesClass();
    }

    public List<Course_Entity> getUnassignedCourses() {
        return DAO.getUnassignedCourses();
    }

    public boolean assignTeacherToCourse(Teachers_Entity teacher, Course_Entity course) {
        return DAO.addTeacherToCourse(teacher, course);
    }

    public Teachers_Entity getTeacherById(Long teacherId) {

        return DAO.findById(teacherId);
    }

    public Course_Entity getCourseByName(String courseName) {

        return DAO.findByName(courseName);
    }

    public boolean addTeacherToCourse(Teachers_Entity teacher, Course_Entity course) {

        return DAO.addTeacherToCourse(teacher, course);
    }

    public List<Course_Entity> getUnassignedCoursesForStudent(Students_Entity student) {
        return DAO.getUnassignedCoursesForStudent(student);
    }

    public Students_Entity getStudentById(Long studentId) {

        return DAO.findstudentById(studentId);
    }

    public boolean addStudentToCourse(Students_Entity student, Course_Entity course) {

        return DAO.addStudentToClass(student, course);
    }

    public Teachers_Entity getTeacherByCourse(Course_Entity course) {
        return DAO.findByCourse(course);
    }

    public boolean inscrireEtudiant(Long studentId, String courseName) {
        return DAO.inscrireEtudiant(studentId, courseName);
    }

    public List<Object[]> getStudentsByCourse(Long courseId) {
        return DAO.getStudentsByCourseId(courseId);
    }

    public List<Object[]> getTeachersByCourse(Long courseId) {
        return DAO.getCoursesByTeacherId(courseId);
    }

    public List<Object[]> getStudentByCourse(Long courseId) {
        return DAO.getCoursesByStudentsId(courseId);
    }

    ;//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Notes
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
      
      public Teachers_Entity Login_Teachers(String regnumb, String password) {
        Teachers_Entity teacher = DAO.Login_Teachers(regnumb, password);
        if (teacher != null) {
            return teacher;
        }
        return null;
    }

    public Long getTeachersIdByRegNumb(String regnumb) {
        Long teacherId = DAO.getTeachersIdByRegNumb(regnumb);
        return teacherId; // Retourne directement l'ID ou null si non trouvé
    }

    public List<String> getCourseNamesByTeacherId(Long teacherId) {
        return DAO.getCourseNamesByTeacherId(teacherId);
    }

    public List<String> getCourseIdByTeacherId(Long teacherId) {
        return DAO.getCourseNamesByTeacherId(teacherId);
    }

    public List<Students_Entity> getStudents(Long courseId, Long teacherId) {
        return DAO.getStudentsByCourseAndTeacher(courseId, teacherId);
    }

    public List<String> getStudentNamesByCourseAndTeacher(Long courseId, Long teacherId) {
        List<Students_Entity> students = DAO.getStudentsByCourseAndTeacher(courseId, teacherId);
        return students.stream().map(s -> s.getLast_name() + " " + s.getFirst_name()).toList();
    }

    public List<Long> getStudentIdsByCourseAndTeacher(Long courseId, Long teacherId) {
        List<Students_Entity> students = DAO.getStudentsByCourseAndTeacher(courseId, teacherId);
        return students.stream().map(Students_Entity::getId).toList();
    }

    public Long getCourseIdByNameAndTeacher(String courseName, Long teacherId) {
        List<Long> courseIds = DAO.findCourseIdByNameAndTeacher(courseName, teacherId);
        return courseIds.isEmpty() ? null : courseIds.get(0);
    }

    public Float getStudentScore(Long studentId, Long courseId) {
        return DAO.findStudentScore(studentId, courseId);
    }

    public void assignStudentScore(Long studentId, Long courseId, float score) {
        DAO.assignStudentScore(studentId, courseId, score);
    }

    public void updateStudentScoreToNull(Long studentId, Long courseId) {
        DAO.updateStudentScoreToNull(studentId, courseId);
    }

//    -------------------------------------------
//    -------------------------------------------
//    Étudiants
//    -------------------------------------------
//    --------------------------------------------
    public Students_Entity Login_Students(String regnumb, String password) {
        Students_Entity student = DAO.Login_Students(regnumb, password);
        if (student != null) {
            return student;
        }
        return null;
    }
    
    public Long getStudentsIdByRegNumb(String regnumb) {
        Long studentsId = DAO.getStudentsIdByRegNumb(regnumb);
        return studentsId; // Retourne directement l'ID ou null si non trouvé
    }
    
    public List<String> getCourseNamesByStudentId(Long studentId) {
        return DAO.getCourseDetailsByStudentId(studentId);
    }
    
    public List<String> getCourseIdByStudentId(Long studentId) {
        return DAO.getCourseDetailsByStudentId(studentId);
    }
    
    public Float calculateAverage(Long studentId) {
        return DAO.calculateAverageGrade(studentId);
    }

    public void close() {
        DAO.close();
    }
}
