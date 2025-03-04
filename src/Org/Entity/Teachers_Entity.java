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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author an
 */
@Entity
@Table(name = "teachers")
public class Teachers_Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reg_number", nullable = false, length = 20, unique = true)
    private String reg_number;

    @Column(name = "last_name", nullable = false, length = 255)
    private String last_name;

    @Column(name = "first_name", nullable = false, length = 255)
    private String first_name;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private Integer phone;

    @Column(name = "sex", nullable = false)
    private Integer sex;

    @Column(name = "speciality", nullable = false, length = 255)
    private String speciality;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "access", nullable = false)
    private Integer access;

    @Column(name = "reg_date", nullable = false)
    private LocalDate reg_date;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Class_Entity> classes = new ArrayList<>();

    public Teachers_Entity() {

    }

    public Teachers_Entity(Long id, String reg_number, String last_name, String first_name, LocalDate dob, String email, Integer phone, Integer sex, String speciality, String password, Integer access, LocalDate reg_date) {
        this.id = id;
        this.reg_number = reg_number;
        this.last_name = last_name;
        this.first_name = first_name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.speciality = speciality;
        this.password = password;
        this.reg_date = reg_date;
        this.access = access;
    }

    public Long getId() {
        return id;
    }

    public String getReg_number() {
        return reg_number;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPhone() {
        return phone;
    }

    public Integer getSex() {
        return sex;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAccess() {
        return access;
    }

    public LocalDate getReg_date() {
        return reg_date;
    }

    public List<Class_Entity> getClasses() {
        return classes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public void setReg_date(LocalDate reg_date) {
        this.reg_date = reg_date;
    }

    public void setClasses(List<Class_Entity> classes) {
        this.classes = classes;
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
        if (!(object instanceof Teachers_Entity)) {
            return false;
        }
        Teachers_Entity other = (Teachers_Entity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Org.Entity.Teacher_Entity[ id=" + id + " ]";
    }

}
