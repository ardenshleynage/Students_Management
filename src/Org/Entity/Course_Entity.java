/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Org.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author an
 */
@Entity
@Table(name = "course")
public class Course_Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = 255, unique = true)
    private String description;

    @Column(name = "access", nullable = false)
    private Integer access;

    @Column(name = "reg_date", nullable = false)
    private LocalDate reg_date;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Class_Entity> classes = new ArrayList<>();

    public Course_Entity() {

    }

    public Course_Entity(Long id, String name, String description, Integer access, LocalDate reg_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.access = access;
        this.reg_date = reg_date;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAccess() {
        return access;
    }

    public List<Class_Entity> getClasses() {
        return classes;
    }

    public LocalDate getReg_date() {
        return reg_date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClasses(List<Class_Entity> classes) {
        this.classes = classes;
    }

    public void setAccess(Integer access) {
        this.access = access;
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
        if (!(object instanceof Course_Entity)) {
            return false;
        }
        Course_Entity other = (Course_Entity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Org.Entity.Course_Entity[ id=" + id + " ]";
    }

}
