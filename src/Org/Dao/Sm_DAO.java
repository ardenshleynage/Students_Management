/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Org.Dao;

import Org.Entity.Class_Entity;
import Org.Entity.Course_Entity;
import Org.Entity.Students_Entity;
import Org.Entity.Super_Admin_Entity;
import Org.Entity.Teachers_Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author an
 */
public class Sm_DAO {

    private EntityManagerFactory emf;

    public Sm_DAO() {
        emf = Persistence.createEntityManagerFactory("Students_ManagementPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Admin    
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    public boolean isAdminExist() {
        EntityManager entityManager = getEntityManager();
        try {
            Long count = entityManager.createQuery("SELECT COUNT(s) FROM Super_Admin_Entity s", Long.class)
                    .getSingleResult();
            return count == 0;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de la table admin : " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    public void addAdmin(Super_Admin_Entity admin) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Super_Admin_Entity Login_Admin(String username, String password) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Super_Admin_Entity> query = em.createQuery(
                    "SELECT s FROM Super_Admin_Entity s WHERE CAST(s.username AS BINARY) = :username AND s.password = :password",
                    Super_Admin_Entity.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);

            List<Super_Admin_Entity> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Étudiants   
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    private String generateRegNumber() {
        EntityManager em = getEntityManager();
        Random random = new Random();
        String regNumber;

        do {
            regNumber = String.format("%06d", random.nextInt(1000000)); // Génère un nombre à 6 chiffres
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(s) FROM Students_Entity s WHERE s.reg_number = :regNumber",
                    Long.class
            );
            query.setParameter("regNumber", regNumber);

            // Vérifie si ce reg_number existe déjà
            if (query.getSingleResult() == 0) {
                break;
            }
        } while (true);

        em.close();
        return regNumber;
    }

    public boolean addStudent(Students_Entity student) throws IllegalArgumentException {
        student.setReg_number(generateRegNumber()); // Génération auto

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(student);
            tx.commit();
            return true;
        } catch (PersistenceException e) { // Intercepte les erreurs de persistance
            if (tx.isActive()) {
                tx.rollback();
            }

            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                if (message.contains("email") || message.contains("phone")) {
                    throw new IllegalArgumentException("Cet étudiant éxiste déjà.");
                } else {
                    throw new IllegalArgumentException("Violation de contrainte d'unicité.");
                }
            }

            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean NoStudents() {
        EntityManager entityManager = getEntityManager();
        try {
            Long count = entityManager.createQuery(
                    "SELECT COUNT(s) FROM Students_Entity s WHERE s.access IN (1, 2)", Long.class)
                    .getSingleResult();
            return count == 0;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des étudiants : " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    public boolean NoTrashStudents() {
        EntityManager entityManager = getEntityManager();
        try {
            Long count = entityManager.createQuery(
                    "SELECT COUNT(s) FROM Students_Entity s WHERE s.access = 0 ", Long.class)
                    .getSingleResult();
            return count == 0;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des étudiants : " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    public List<Students_Entity> getAllStudents() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Students_Entity> query = em.createQuery("SELECT s FROM Students_Entity s WHERE s.access  IN (1, 2)", Students_Entity.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Students_Entity> getAllSearchStudents() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Students_Entity> query = em.createQuery("SELECT s FROM Students_Entity s WHERE s.access  IN (0, 1, 2)", Students_Entity.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Students_Entity> getAllTrashStudents() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Students_Entity> query = em.createQuery("SELECT s FROM Students_Entity s WHERE s.access  = 0", Students_Entity.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public boolean BlockStudents(Students_Entity stu) {
        if (stu == null) {
            throw new IllegalArgumentException("L'objet Students_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Students_Entity student = em.find(Students_Entity.class, stu.getId());

            if (student == null) {
                return false;
            }
            student.setAccess(2);
            em.merge(student);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean AllowStudents(Students_Entity stu) {
        if (stu == null) {
            throw new IllegalArgumentException("L'objet Students_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Students_Entity student = em.find(Students_Entity.class, stu.getId());

            if (student == null) {
                return false;
            }
            student.setAccess(1);
            em.merge(student);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean SureDeleteStudents(Students_Entity stu) {
        if (stu == null) {
            throw new IllegalArgumentException("L'objet Students_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Students_Entity student = em.find(Students_Entity.class, stu.getId());

            if (student == null) {
                return false;
            }
            student.setAccess(0);
            em.merge(student);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean RestoreStudents(Students_Entity stu) {
        if (stu == null) {
            throw new IllegalArgumentException("L'objet Students_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Students_Entity student = em.find(Students_Entity.class, stu.getId());

            if (student == null) {
                return false;
            }
            student.setAccess(1);
            em.merge(student);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureEraseStudents(Students_Entity stu) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            stu = em.merge(stu);
            em.remove(stu);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateLastNameStudents(Students_Entity student) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (student.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'étudiant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Students_Entity existingStudent = em.find(Students_Entity.class, student.getId());

            if (existingStudent != null) {
                // Mise à jour du nom de l'étudiant
                existingStudent.setLast_name(student.getLast_name());
                em.merge(existingStudent);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateFirstNameStudents(Students_Entity student) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (student.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'étudiant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Students_Entity existingStudent = em.find(Students_Entity.class, student.getId());

            if (existingStudent != null) {
                // Mise à jour du nom de l'étudiant
                existingStudent.setFirst_name(student.getFirst_name());
                em.merge(existingStudent);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateAddressStudents(Students_Entity student) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (student.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'étudiant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Students_Entity existingStudent = em.find(Students_Entity.class, student.getId());

            if (existingStudent != null) {
                // Mise à jour du nom de l'étudiant
                existingStudent.setAddress(student.getAddress());
                em.merge(existingStudent);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateEmailStudents(Students_Entity student) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (student.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'étudiant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Students_Entity existingStudent = em.find(Students_Entity.class, student.getId());

            if (existingStudent != null) {
                // Mise à jour de l'email de l'étudiant
                existingStudent.setEmail(student.getEmail());
                em.merge(existingStudent);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, rollback et retourne false
            em.getTransaction().rollback();
            return false;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                if (message.contains("email")) {
                    throw new IllegalArgumentException("Cet e-mail existe déjà.");
                } else {
                    throw new IllegalArgumentException("Violation de contrainte d'unicité.");
                }
            }

            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateDobStudents(Students_Entity student) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (student.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'étudiant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Students_Entity existingStudent = em.find(Students_Entity.class, student.getId());

            if (existingStudent != null) {
                // Mise à jour du nom de l'étudiant
                existingStudent.setDob(student.getDob());
                em.merge(existingStudent);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateSexStudents(Students_Entity student) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (student.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'étudiant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Students_Entity existingStudent = em.find(Students_Entity.class, student.getId());

            if (existingStudent != null) {
                // Mise à jour du nom de l'étudiant
                existingStudent.setSex(student.getSex());
                em.merge(existingStudent);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updatePhoneStudents(Students_Entity student) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (student.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'étudiant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Students_Entity existingStudent = em.find(Students_Entity.class, student.getId());

            if (existingStudent != null) {
                // Mise à jour de l'email de l'étudiant
                existingStudent.setPhone(student.getPhone());
                em.merge(existingStudent);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, rollback et retourne false
            em.getTransaction().rollback();
            return false;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                if (message.contains("phone")) {
                    throw new IllegalArgumentException("Ce numéro de téléphone existe déjà.");
                } else {
                    throw new IllegalArgumentException("Violation de contrainte d'unicité.");
                }
            }

            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updatePasswordStudents(Students_Entity student) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (student.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'étudiant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Students_Entity existingStudent = em.find(Students_Entity.class, student.getId());

            if (existingStudent != null) {
                // Mise à jour du nom de l'étudiant
                existingStudent.setPassword(student.getPassword());
                em.merge(existingStudent);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateRegNumbStudents(Students_Entity student) {
        EntityManager em = getEntityManager();
        try {
            if (student.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'étudiant est null.");
            }

            em.getTransaction().begin();
            Students_Entity existingStudent = em.find(Students_Entity.class, student.getId());

            if (existingStudent != null) {
                // Vérification si la matricule est unique
                TypedQuery<Long> query = em.createQuery(
                        "SELECT COUNT(s) FROM Students_Entity s WHERE s.reg_number = :regNumber", Long.class
                );
                query.setParameter("regNumber", student.getReg_number());
                if (query.getSingleResult() > 0) {
                    throw new IllegalArgumentException("Cette matricule existe déjà.");
                }

                existingStudent.setReg_number(student.getReg_number());
                em.merge(existingStudent);
                em.getTransaction().commit();
                return true;
            }

            em.getTransaction().rollback();
            return false;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public String generateUniqueRegNumber() {
        EntityManager em = getEntityManager();
        Random random = new Random();
        String regNumber;

        try {
            do {
                regNumber = String.format("%06d", random.nextInt(1000000)); // Génère un nombre à 6 chiffres
                TypedQuery<Long> query = em.createQuery(
                        "SELECT COUNT(s) FROM Students_Entity s WHERE s.reg_number = :regNumber",
                        Long.class
                );
                query.setParameter("regNumber", regNumber);

                // Vérifie si ce reg_number existe déjà
                if (query.getSingleResult() == 0) {
                    break;
                }
            } while (true);
        } finally {
            em.close();
        }

        return regNumber;
    }

    public Students_Entity findStudentByRegNumber(String regNumber) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Students_Entity> query = em.createQuery(
                    "SELECT s FROM Students_Entity s WHERE s.reg_number = :regNumber", Students_Entity.class);
            query.setParameter("regNumber", regNumber);
            return query.getSingleResult(); // Retourne un objet Students_Entity
        } catch (NoResultException e) {
            return null; // Aucun étudiant trouvé
        } finally {
            em.close();
        }
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Enseignants   
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    private String generateRegNumberTeachers() {
        EntityManager em = getEntityManager();
        Random random = new Random();
        String regNumber;

        do {
            regNumber = String.format("%06d", random.nextInt(1000000)); // Génère un nombre à 6 chiffres
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(t) FROM Teachers_Entity t WHERE t.reg_number = :regNumber",
                    Long.class
            );
            query.setParameter("regNumber", regNumber);

            // Vérifie si ce reg_number existe déjà
            if (query.getSingleResult() == 0) {
                break;
            }
        } while (true);

        em.close();
        return regNumber;
    }

    public boolean addTeachers(Teachers_Entity teacher) throws IllegalArgumentException {
        teacher.setReg_number(generateRegNumberTeachers()); // Génération auto

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(teacher);
            tx.commit();
            return true;
        } catch (PersistenceException e) { // Intercepte les erreurs de persistance
            if (tx.isActive()) {
                tx.rollback();
            }

            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                if (message.contains("email") || message.contains("phone")) {
                    throw new IllegalArgumentException("Cet enseignant éxiste déjà.");
                } else {
                    throw new IllegalArgumentException("Violation de contrainte d'unicité.");
                }
            }

            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean NoTeachers() {
        EntityManager entityManager = getEntityManager();
        try {
            Long count = entityManager.createQuery(
                    "SELECT COUNT(t) FROM Teachers_Entity t WHERE t.access IN (1, 2)", Long.class)
                    .getSingleResult();
            return count == 0;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des enseignats : " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    public List<Teachers_Entity> getAllTeachers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Teachers_Entity> query = em.createQuery("SELECT t FROM Teachers_Entity t WHERE t.access  IN (1, 2)", Teachers_Entity.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockTeachers(Teachers_Entity tea) {
        if (tea == null) {
            throw new IllegalArgumentException("L'objet Teachers_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Teachers_Entity teacher = em.find(Teachers_Entity.class, tea.getId());

            if (teacher == null) {
                return false;
            }
            teacher.setAccess(2);
            em.merge(teacher);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllowTeachers(Teachers_Entity tea) {
        if (tea == null) {
            throw new IllegalArgumentException("L'objet Teachers_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Teachers_Entity teachers = em.find(Teachers_Entity.class, tea.getId());

            if (teachers == null) {
                return false;
            }
            teachers.setAccess(1);
            em.merge(teachers);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteTeachers(Teachers_Entity tea) {
        if (tea == null) {
            throw new IllegalArgumentException("L'objet Teachers_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Teachers_Entity teacher = em.find(Teachers_Entity.class, tea.getId());

            if (teacher == null) {
                return false;
            }
            teacher.setAccess(0);
            em.merge(teacher);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public Teachers_Entity findTeachersByRegNumber(String regNumber) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Teachers_Entity> query = em.createQuery(
                    "SELECT t FROM Teachers_Entity t WHERE t.reg_number = :regNumber", Teachers_Entity.class);
            query.setParameter("regNumber", regNumber);
            return query.getSingleResult(); // Retourne un objet Students_Entity
        } catch (NoResultException e) {
            return null; // Aucun étudiant trouvé
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch"})
    public boolean updateLastNameTeachers(Teachers_Entity teacher) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'enseignant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Teachers_Entity existingTeachers = em.find(Teachers_Entity.class, teacher.getId());

            if (existingTeachers != null) {
                // Mise à jour du nom de l'étudiant
                existingTeachers.setLast_name(teacher.getLast_name());
                em.merge(existingTeachers);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateFirstNameTeachers(Teachers_Entity teacher) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'enseignant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Teachers_Entity existingTeachers = em.find(Teachers_Entity.class, teacher.getId());

            if (existingTeachers != null) {
                // Mise à jour du nom de l'étudiant
                existingTeachers.setFirst_name(teacher.getFirst_name());
                em.merge(existingTeachers);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateDobTeachers(Teachers_Entity teacher) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'enseignant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Teachers_Entity existingTeacher = em.find(Teachers_Entity.class, teacher.getId());

            if (existingTeacher != null) {
                // Mise à jour du nom de l'étudiant
                existingTeacher.setDob(teacher.getDob());
                em.merge(existingTeacher);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateSexTeachers(Teachers_Entity teacher) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'enseignant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Teachers_Entity existingTeacher = em.find(Teachers_Entity.class, teacher.getId());

            if (existingTeacher != null) {
                // Mise à jour du nom de l'étudiant
                existingTeacher.setSex(teacher.getSex());
                em.merge(existingTeacher);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateEmailTeachers(Teachers_Entity teacher) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'enseignant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Teachers_Entity existingTeacher = em.find(Teachers_Entity.class, teacher.getId());

            if (existingTeacher != null) {
                // Mise à jour de l'email de l'étudiant
                existingTeacher.setEmail(teacher.getEmail());
                em.merge(existingTeacher);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, rollback et retourne false
            em.getTransaction().rollback();
            return false;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                if (message.contains("email")) {
                    throw new IllegalArgumentException("Cet e-mail existe déjà.");
                } else {
                    throw new IllegalArgumentException("Violation de contrainte d'unicité.");
                }
            }

            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateSpecialityTeachers(Teachers_Entity teacher) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'enseignant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Teachers_Entity existingTeacher = em.find(Teachers_Entity.class, teacher.getId());

            if (existingTeacher != null) {
                // Mise à jour du nom de l'étudiant
                existingTeacher.setSpeciality(teacher.getSpeciality());
                em.merge(existingTeacher);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updatePhoneTeachers(Teachers_Entity teacher) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'enseigant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Teachers_Entity existingTeacher = em.find(Teachers_Entity.class, teacher.getId());

            if (existingTeacher != null) {
                // Mise à jour de l'email de l'étudiant
                existingTeacher.setPhone(teacher.getPhone());
                em.merge(existingTeacher);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, rollback et retourne false
            em.getTransaction().rollback();
            return false;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                if (message.contains("phone")) {
                    throw new IllegalArgumentException("Ce numéro de téléphone existe déjà.");
                } else {
                    throw new IllegalArgumentException("Violation de contrainte d'unicité.");
                }
            }

            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updatePasswordTeachers(Teachers_Entity teacher) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'enseignant est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Teachers_Entity existingTeacher = em.find(Teachers_Entity.class, teacher.getId());

            if (existingTeacher != null) {
                // Mise à jour du nom de l'étudiant
                existingTeacher.setPassword(teacher.getPassword());
                em.merge(existingTeacher);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, on annule la transaction
            em.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            // En cas d'erreur, rollback de la transaction et gestion de l'exception
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public String generateUniqueRegNumberTeachers() {
        EntityManager em = getEntityManager();
        Random random = new Random();
        String regNumber;

        try {
            do {
                regNumber = String.format("%06d", random.nextInt(1000000)); // Génère un nombre à 6 chiffres
                TypedQuery<Long> query = em.createQuery(
                        "SELECT COUNT(t) FROM Teachers_Entity t WHERE t.reg_number = :regNumber",
                        Long.class
                );
                query.setParameter("regNumber", regNumber);

                // Vérifie si ce reg_number existe déjà
                if (query.getSingleResult() == 0) {
                    break;
                }
            } while (true);
        } finally {
            em.close();
        }

        return regNumber;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateRegNumbStudentsTeachers(Teachers_Entity teacher) {
        EntityManager em = getEntityManager();
        try {
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("L'ID de l'enseignant est null.");
            }

            em.getTransaction().begin();
            Teachers_Entity existingTeacher = em.find(Teachers_Entity.class, teacher.getId());

            if (existingTeacher != null) {
                // Vérification si la matricule est unique
                TypedQuery<Long> query = em.createQuery(
                        "SELECT COUNT(t) FROM Teachers_Entity t WHERE t.reg_number = :regNumber", Long.class
                );
                query.setParameter("regNumber", teacher.getReg_number());
                if (query.getSingleResult() > 0) {
                    throw new IllegalArgumentException("Cette matricule existe déjà.");
                }

                existingTeacher.setReg_number(teacher.getReg_number());
                em.merge(existingTeacher);
                em.getTransaction().commit();
                return true;
            }

            em.getTransaction().rollback();
            return false;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean NoTrashTeachers() {
        EntityManager entityManager = getEntityManager();
        try {
            Long count = entityManager.createQuery(
                    "SELECT COUNT(t) FROM Teachers_Entity t WHERE t.access = 0 ", Long.class)
                    .getSingleResult();
            return count == 0;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des enseignants : " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    public List<Teachers_Entity> getAllTrashTeachers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Teachers_Entity> query = em.createQuery("SELECT t FROM Teachers_Entity t WHERE t.access  = 0", Teachers_Entity.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureEraseTeachers(Teachers_Entity tea) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            tea = em.merge(tea);
            em.remove(tea);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean RestoreTeachers(Teachers_Entity tea) {
        if (tea == null) {
            throw new IllegalArgumentException("L'objet Teachers_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Teachers_Entity teacher = em.find(Teachers_Entity.class, tea.getId());

            if (teacher == null) {
                return false;
            }
            teacher.setAccess(1);
            em.merge(teacher);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public List<Teachers_Entity> getAllSearchTeachers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Teachers_Entity> query = em.createQuery("SELECT t FROM Teachers_Entity t WHERE t.access  IN (0, 1, 2)", Teachers_Entity.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Courss    
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean addCourse(Course_Entity course) throws IllegalArgumentException {

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(course);
            tx.commit();
            return true;
        } catch (PersistenceException e) { // Intercepte les erreurs de persistance
            if (tx.isActive()) {
                tx.rollback();
            }

            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                if (message.contains("name") || message.contains("description")) {
                    throw new IllegalArgumentException("Ce cours éxiste déjà.");
                } else {
                    throw new IllegalArgumentException("Violation de contrainte d'unicité.");
                }
            }

            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean NoCourse() {
        EntityManager entityManager = getEntityManager();
        try {
            Long count = entityManager.createQuery(
                    "SELECT COUNT(c) FROM Course_Entity c WHERE c.access IN (1, 2)", Long.class)
                    .getSingleResult();
            return count == 0;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des cours : " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    public List<Course_Entity> getAllCourse() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Course_Entity> query = em.createQuery("SELECT c FROM Course_Entity c WHERE c.access  IN (1, 2)", Course_Entity.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockCourse(Course_Entity cou) {
        if (cou == null) {
            throw new IllegalArgumentException("L'objet Course_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Course_Entity course = em.find(Course_Entity.class, cou.getId());

            if (course == null) {
                return false;
            }
            course.setAccess(2);
            em.merge(course);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllowCourse(Course_Entity cou) {
        if (cou == null) {
            throw new IllegalArgumentException("L'objet Course_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Course_Entity course = em.find(Course_Entity.class, cou.getId());

            if (course == null) {
                return false;
            }
            course.setAccess(1);
            em.merge(course);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean SureDeleteCourse(Course_Entity cou) {
        if (cou == null) {
            throw new IllegalArgumentException("L'objet Course_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Course_Entity course = em.find(Course_Entity.class, cou.getId());

            if (course == null) {
                return false;
            }
            course.setAccess(0);
            em.merge(course);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public Course_Entity findCourseByName(String Name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Course_Entity> query = em.createQuery(
                    "SELECT c FROM Course_Entity c WHERE c.name = :Name", Course_Entity.class);
            query.setParameter("Name", Name);
            return query.getSingleResult(); // Retourne un objet Students_Entity
        } catch (NoResultException e) {
            return null; // Aucun étudiant trouvé
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateNameCourse(Course_Entity course) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (course.getId() == null) {
                throw new IllegalArgumentException("L'ID du cours est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Course_Entity existingCourse = em.find(Course_Entity.class, course.getId());

            if (existingCourse != null) {
                // Mise à jour de l'email de l'étudiant
                existingCourse.setName(course.getName());
                em.merge(existingCourse);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, rollback et retourne false
            em.getTransaction().rollback();
            return false;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                if (message.contains("name")) {
                    throw new IllegalArgumentException("Ce nom existe déjà.");
                } else {
                    throw new IllegalArgumentException("Violation de contrainte d'unicité.");
                }
            }

            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updateDescriptionCourse(Course_Entity course) {
        EntityManager em = getEntityManager();
        try {
            // Vérifie si l'ID de l'étudiant est valide (non null)
            if (course.getId() == null) {
                throw new IllegalArgumentException("L'ID du cours est null.");
            }

            em.getTransaction().begin();

            // Recherche l'étudiant dans la base de données en fonction de l'ID
            Course_Entity existingCourse = em.find(Course_Entity.class, course.getId());

            if (existingCourse != null) {
                // Mise à jour de l'email de l'étudiant
                existingCourse.setDescription(course.getDescription());
                em.merge(existingCourse);
                em.getTransaction().commit();
                return true;
            }

            // Si l'étudiant n'existe pas, rollback et retourne false
            em.getTransaction().rollback();
            return false;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            String message = e.getMessage().toLowerCase();
            if (message.contains("unique") || message.contains("duplicate")) {
                if (message.contains("description")) {
                    throw new IllegalArgumentException("Cette description existe déjà.");
                } else {
                    throw new IllegalArgumentException("Violation de contrainte d'unicité.");
                }
            }

            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean RestoreCourse(Course_Entity cou) {
        if (cou == null) {
            throw new IllegalArgumentException("L'objet Course_Entity ne peut pas être null");
        }

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Course_Entity course = em.find(Course_Entity.class, cou.getId());

            if (course == null) {
                return false;
            }
            course.setAccess(1);
            em.merge(course);

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean NoTrashCourse() {
        EntityManager entityManager = getEntityManager();
        try {
            Long count = entityManager.createQuery(
                    "SELECT COUNT(c) FROM Course_Entity c WHERE c.access = 0 ", Long.class)
                    .getSingleResult();
            return count == 0;
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification des cours: " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    public List<Course_Entity> getAllTrashCourse() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Course_Entity> query = em.createQuery("SELECT c FROM Course_Entity c WHERE c.access  = 0", Course_Entity.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureEraseCourse(Course_Entity cou) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            cou = em.merge(cou);
            em.remove(cou);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Classes
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    @SuppressWarnings("CallToPrintStackTrace")
    public List<Course_Entity> getAllCoursesClass() {
        EntityManager em = emf.createEntityManager();
        List<Course_Entity> courses = null;

        try {
            TypedQuery<Course_Entity> query = em.createQuery("SELECT c FROM Course_Entity c", Course_Entity.class);
            courses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Afficher les erreurs si quelque chose ne fonctionne pas
        } finally {
            em.close();
        }
        return courses;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Course_Entity> getUnassignedCourses() {
        EntityManager em = emf.createEntityManager();
        List<Course_Entity> courses = null;
        try {
            TypedQuery<Course_Entity> query = em.createQuery(
                    "SELECT c FROM Course_Entity c WHERE c.access = 1 AND c.id NOT IN (SELECT cl.course.id FROM Class_Entity cl)",
                    Course_Entity.class
            );
            courses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return courses;
    }

    // ✅ Ajouter un enseignant et un cours dans la table classes
    public boolean addTeacherToCourse(Teachers_Entity teacher, Course_Entity course) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Vérifier si l'enseignant existe déjà dans la base de données par son e-mail
            Teachers_Entity existingTeacher = em.createQuery(
                    "SELECT t FROM Teachers_Entity t WHERE t.email = :email", Teachers_Entity.class)
                    .setParameter("email", teacher.getEmail())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            // Si l'enseignant existe, utiliser l'enseignant existant
            if (existingTeacher != null) {
                teacher = existingTeacher; // Utiliser l'enseignant déjà existant
            } else {
                // Si l'enseignant n'existe pas, persister le nouvel enseignant
                em.persist(teacher);
            }

            // Vérification si le cours existe déjà dans la base de données
            Course_Entity existingCourse = em.createQuery(
                    "SELECT c FROM Course_Entity c WHERE c.description = :description", Course_Entity.class)
                    .setParameter("description", course.getDescription())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            // Si le cours existe, utiliser celui existant
            if (existingCourse != null) {
                course = existingCourse; // Utiliser le cours déjà existant
            } else {
                // Si le cours n'existe pas, persister le nouveau cours
                em.persist(course);
            }

            // Création de l'association dans la table 'class'
            Class_Entity newClass = new Class_Entity();
            newClass.setTeacher(teacher);
            newClass.setCourse(course);
            newClass.setReg_date(java.time.LocalDate.now());
            newClass.setAccess(1); // Par défaut, accès activé

            em.persist(newClass);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public Teachers_Entity findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Teachers_Entity.class, id);
        } finally {
            em.close();
        }
    }

    public Course_Entity findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Course_Entity> query = em.createQuery(
                    "SELECT c FROM Course_Entity c WHERE c.name = :name", Course_Entity.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Students_Entity findstudentById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Students_Entity.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Course_Entity> getUnassignedCoursesForStudent(Students_Entity student) {
        EntityManager em = emf.createEntityManager();
        List<Course_Entity> courses = null;
        try {
            TypedQuery<Course_Entity> query = em.createQuery(
                    "SELECT c FROM Course_Entity c WHERE c.access = 1 AND c.id NOT IN "
                    + "(SELECT cl.course.id FROM Class_Entity cl WHERE cl.student.id = :studentId)",
                    Course_Entity.class
            );
            query.setParameter("studentId", student.getId());
            courses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return courses;
    }

    public boolean addStudentToClass(Students_Entity student, Course_Entity course) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Vérifier si l'étudiant est déjà inscrit à ce cours dans une classe
            Long existingEnrollment = em.createQuery(
                    "SELECT COUNT(cl) FROM Class_Entity cl WHERE cl.course = :course AND cl.student = :student",
                    Long.class
            )
                    .setParameter("course", course)
                    .setParameter("student", student)
                    .getSingleResult();

            if (existingEnrollment > 0) {
                System.out.println("L'étudiant est déjà inscrit à ce cours dans cette classe.");
                return false;
            }

            // Vérifier si la classe existe pour ce cours
            Class_Entity existingClass = em.createQuery(
                    "SELECT c FROM Class_Entity c WHERE c.course = :course AND c.teacher IS NOT NULL",
                    Class_Entity.class
            )
                    .setParameter("course", course)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (existingClass == null) {
                System.out.println("Aucune classe existante pour ce cours.");
                return false;
            }

            // Ajouter l'étudiant à la classe
            existingClass.setStudent(student);
            em.merge(existingClass);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean inscrireEtudiant(Long studentId, String courseName) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Vérifier si l'étudiant existe
            Students_Entity student = em.find(Students_Entity.class, studentId);
            if (student == null) {
                System.out.println("Étudiant introuvable avec l'ID : " + studentId);
                return false;
            }

            // Récupérer le cours par son nom
            Course_Entity course = em.createQuery(
                    "SELECT c FROM Course_Entity c WHERE c.name = :name", Course_Entity.class)
                    .setParameter("name", courseName)
                    .getSingleResult();

            // Récupérer le professeur associé au cours
            Teachers_Entity teacher = em.createQuery(
                    "SELECT c.teacher FROM Class_Entity c WHERE c.course.id = :courseId", Teachers_Entity.class)
                    .setParameter("courseId", course.getId())
                    .setMaxResults(1) // S'assurer de ne récupérer qu'un seul résultat
                    .getSingleResult();

            // Vérifier si l'association existe déjà
            Long count = em.createQuery(
                    "SELECT COUNT(c) FROM Class_Entity c WHERE c.course.id = :courseId AND c.teacher.id = :teacherId AND c.student.id = :studentId",
                    Long.class)
                    .setParameter("courseId", course.getId())
                    .setParameter("teacherId", teacher.getId())
                    .setParameter("studentId", student.getId())
                    .getSingleResult();

            if (count > 0) {
                System.out.println("L'étudiant est déjà inscrit à ce cours avec ce professeur.");
                return false;
            }

            // Ajouter une nouvelle entrée dans `Class_Entity`
            Class_Entity newClass = new Class_Entity();
            newClass.setCourse(course);
            newClass.setTeacher(teacher);
            newClass.setStudent(student);
            if (newClass.getAccess() == null && newClass.getReg_date() == null) {
                newClass.setAccess(1); // Affecter la valeur 1 si "access" est null
                newClass.setReg_date(LocalDate.now());
            }

            em.persist(newClass);
            em.getTransaction().commit();
            return true;
        } catch (NoResultException e) {
            System.out.println("Cours ou professeur non trouvé !");
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public Teachers_Entity findByCourse(Course_Entity course) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c.teacher FROM Class_Entity c WHERE c.course = :course",
                    Teachers_Entity.class
            )
                    .setParameter("course", course)
                    .setMaxResults(1) // Optionnel, si un seul enseignant par cours est attendu
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Object[]> getStudentsByCourseId(Long courseId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT s.id, s.first_name, s.last_name "
                    + "FROM Class_Entity c "
                    + "JOIN c.student s "
                    + "WHERE c.course.id = :courseId AND s.access = 1", Object[].class)
                    .setParameter("courseId", courseId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getCoursesByTeacherId(Long teacherId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT c.id, c.name "
                    + // Utiliser DISTINCT pour récupérer des cours uniques
                    "FROM Class_Entity cl "
                    + "JOIN cl.course c "
                    + "WHERE cl.teacher.id = :teacherId "
                    + "AND c.access = 1", Object[].class)
                    .setParameter("teacherId", teacherId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> getCoursesByStudentsId(Long studentId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT c.id, c.name "
                    + // Utiliser DISTINCT pour récupérer des cours uniques
                    "FROM Class_Entity cl "
                    + "JOIN cl.course c "
                    + "WHERE cl.student.id = :studentId "
                    + "AND c.access = 1", Object[].class)
                    .setParameter("studentId", studentId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//                                      Notes
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
//    ----------------------------------------------------------------------------
    public Teachers_Entity Login_Teachers(String regnumb, String password) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Teachers_Entity> query = em.createQuery(
                    "SELECT t FROM Teachers_Entity t WHERE CAST(t.reg_number AS BINARY) = :regnumb AND t.password = :password AND t.access=1",
                    Teachers_Entity.class
            );
            query.setParameter("regnumb", regnumb);
            query.setParameter("password", password);

            List<Teachers_Entity> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    public Long getTeachersIdByRegNumb(String regnumb) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT t.id FROM Teachers_Entity t WHERE CAST(t.reg_number AS BINARY) = :regnumb",
                    Long.class
            );
            query.setParameter("regnumb", regnumb);

            List<Long> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    public List<String> getCourseNamesByTeacherId(Long teacherId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT DISTINCT c.name "
                    + "FROM Course_Entity c "
                    + "JOIN Class_Entity cl ON c.id = cl.course.id "
                    + "WHERE cl.teacher.id = :teacherId AND c.access = 1",
                    String.class
            );

            query.setParameter("teacherId", teacherId);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<String> getCourseIdByTeacherId(Long teacherId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT DISTINCT c.id "
                    + "FROM Course_Entity c "
                    + "JOIN Class_Entity cl ON c.id = cl.course.id "
                    + "WHERE cl.teacher.id = :teacherId AND c.access = 1",
                    String.class
            );

            query.setParameter("teacherId", teacherId);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Students_Entity> getStudentsByCourseAndTeacher(Long courseId, Long teacherId) {
        EntityManager em = getEntityManager();
        String jpql = "SELECT c.student FROM Class_Entity c "
                + "WHERE c.course.id = :courseId "
                + "AND c.teacher.id = :teacherId "
                + "AND c.student.access = 1 "
                + "AND c.course.access = 1";

        TypedQuery<Students_Entity> query = em.createQuery(jpql, Students_Entity.class);
        query.setParameter("courseId", courseId);
        query.setParameter("teacherId", teacherId);

        return query.getResultList();
    }

    public List<Long> findCourseIdByNameAndTeacher(String courseName, Long teacherId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c.id FROM Course_Entity c "
                    + "JOIN Class_Entity cl ON c.id = cl.course.id "
                    + // Liaison via class_entity
                    "WHERE c.name = :courseName "
                    + "AND cl.teacher.id = :teacherId "
                    + // Vérifie l'ID du teacher dans class_entity
                    "AND c.access = 1 "
                    + // Le cours doit être actif
                    "AND cl.access = 1", // La classe doit être active
                    Long.class)
                    .setParameter("courseName", courseName)
                    .setParameter("teacherId", teacherId)
                    .getResultList();
        } catch (NoResultException e) {
            return null; // Aucun résultat trouvé
        }
    }

    public Float findStudentScore(Long studentId, Long courseId) {
        try {
            EntityManager em = getEntityManager();
            return em.createQuery(
                    "SELECT c.score FROM Class_Entity c WHERE c.student.id = :studentId AND c.course.id = :courseId",
                    Float.class)
                    .setParameter("studentId", studentId)
                    .setParameter("courseId", courseId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Retourne null si aucune note trouvée
        }
    }

    public void assignStudentScore(Long studentId, Long courseId, float score) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Recherche de l'entité correspondante
            try {
                Class_Entity classEntity = em.createQuery(
                        "SELECT c FROM Class_Entity c WHERE c.student.id = :studentId AND c.course.id = :courseId",
                        Class_Entity.class)
                        .setParameter("studentId", studentId)
                        .setParameter("courseId", courseId)
                        .getSingleResult();

                // Mise à jour de la note
                classEntity.setScore(score);
                em.merge(classEntity);

                em.getTransaction().commit();
            } catch (NoResultException e) {
                System.out.println("Erreur : Aucun enregistrement trouvé pour cet étudiant et ce cours.");
                em.getTransaction().rollback();
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateStudentScoreToNull(Long studentId, Long courseId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Recherche de l'entité correspondante
            try {
                Class_Entity classEntity = em.createQuery(
                        "SELECT c FROM Class_Entity c WHERE c.student.id = :studentId AND c.course.id = :courseId",
                        Class_Entity.class)
                        .setParameter("studentId", studentId)
                        .setParameter("courseId", courseId)
                        .getSingleResult();

                // Mise à jour : suppression de la note (score = null)
                classEntity.setScore(null);
                em.merge(classEntity);

                em.getTransaction().commit();
            } catch (NoResultException e) {
                System.out.println("Erreur : Aucun enregistrement trouvé pour cet étudiant et ce cours.");
                em.getTransaction().rollback();
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

//    -------------------------------------------
//    -------------------------------------------
//    Étudiants
//    -------------------------------------------
//    --------------------------------------------
    public Students_Entity Login_Students(String regnumb, String password) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Students_Entity> query = em.createQuery(
                    "SELECT s FROM Students_Entity s WHERE CAST(s.reg_number AS BINARY) = :regnumb AND s.password = :password AND s.access=1",
                    Students_Entity.class
            );
            query.setParameter("regnumb", regnumb);
            query.setParameter("password", password);

            List<Students_Entity> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    public Long getStudentsIdByRegNumb(String regnumb) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT s.id FROM Students_Entity s WHERE CAST(s.reg_number AS BINARY) = :regnumb",
                    Long.class
            );
            query.setParameter("regnumb", regnumb);

            List<Long> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    public List<String> getCourseDetailsByStudentId(Long studentId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Object[]> query = em.createQuery(
                    "SELECT c.name, t.first_name, t.last_name, cl.score "
                    + // Vérifier le champ `firstName`
                    "FROM Class_Entity cl "
                    + "JOIN cl.course c "
                    + // Syntaxe JPQL correcte
                    "JOIN cl.teacher t "
                    + "WHERE cl.student.id = :studentId "
                    + "AND c.access = 1 "
                    + "AND t.access = 1",
                    Object[].class
            );

            query.setParameter("studentId", studentId);

            List<Object[]> results = query.getResultList();
            List<String> formattedResults = new ArrayList<>();

            for (Object[] row : results) {
                String courseName = (String) row[0];
                String teacherFName = (String) row[1];
                String teacherLName = (String) row[2];

                // Vérification et conversion du score
                Float score = (row[3] != null) ? ((Number) row[3]).floatValue() : null;

                String formattedResult = "Cours : " + courseName
                        + ", Professeur : " + teacherFName + " " + teacherLName
                        + ", Score : " + (score != null ? score : "Inconnu");

                formattedResults.add(formattedResult);
            }

            return formattedResults;

        } finally {
            em.close();
        }
    }

    public List<Float> getGradesByStudentId(Long studentId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Float> query = em.createQuery(
                    "SELECT cl.score "
                    + "FROM Class_Entity cl "
                    + "JOIN cl.course c "
                    + "JOIN cl.teacher t "
                    + "WHERE cl.student.id = :studentId "
                    + "AND c.access = 1 "
                    + "AND t.access = 1",
                    Float.class
            );

            query.setParameter("studentId", studentId);

            List<Float> results = query.getResultList();
            return results;

        } finally {
            em.close();
        }
    }

    public Float calculateAverageGrade(Long studentId) {
        List<Float> grades = getGradesByStudentId(studentId);

        float sum = 0;
        int count = 0;

        for (Float grade : grades) {
            if (grade != null) { // Ignorer les notes nulles
                sum += grade;
                count++;
            }
        }

        return (count > 0) ? (sum / count) : 0; // Retourne 0 si aucune note valide
    }

    public void close() {
        emf.close();
    }
}
