/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Org.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author an
 */
@Entity
@Table(name = "classes")
public class Class_Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teachers_Entity teacher;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id",nullable = true)
    private Students_Entity student;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "course_id", nullable = false)
    private Course_Entity course;

    @Column(name = "access", nullable = false)
    private Integer access;
    
    @Column(name = "score", nullable = true)
    private Float score;

    @Column(name = "reg_date", nullable = false)
    private LocalDate reg_date;

    public Class_Entity() {

    }

    public Class_Entity(Long id, Teachers_Entity teacher, Students_Entity student, Course_Entity course, Integer access, Float score,LocalDate reg_date) {
        this.id = id;
        this.teacher = teacher;
        this.student = student;
        this.course = course;
        this.access = access;
        this.score=score;
        this.reg_date = reg_date;
    }

    public Long getId() {
        return id;
    }

    public Teachers_Entity getTeacher() {
        return teacher;
    }

    public Students_Entity getStudent() {
        return student;
    }

    public Course_Entity getCourse() {
        return course;
    }

    public Integer getAccess() {
        return access;
    }

    public Float getScore() {
        return score;
    }
    

    public LocalDate getReg_date() {
        return reg_date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeacher(Teachers_Entity teacher) {
        this.teacher = teacher;
    }

    public void setStudent(Students_Entity student) {
        this.student = student;
    }

    public void setCourse(Course_Entity course) {
        this.course = course;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public void setScore(Float score) {
        this.score = score;
    }
    

    public void setReg_date(LocalDate reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Class_Entity)) {
            return false;
        }
        Class_Entity other = (Class_Entity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Org.Entity.Class_Entity[ id=" + id + " ]";
    }

}
