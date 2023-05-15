package com.medipatient.medipatient.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false)
    private Integer id;

    @Column(name="FIRSTNAME", length=50)
    private String firstname;

    @Column(name="LASTNAME", length=50)
    private String lastname;

    @Column(name="DOB")
    private LocalDate dob;

    @Column(name="SEX")
    private String sex;

    @Column(name="ADDRESS", length=250)
    private String address;

    @Column(name="PHONE", length=20)
    private String phone;

    public Patient() {
    }

    public Patient(Integer id, String firstname, String lastname, LocalDate dob, String sex, String address, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", prenom='" + firstname + '\'' +
                ", nom='" + lastname + '\'' +
                ", datenaiss=" + dob +
                ", genre=" + sex +
                ", adressepostale='" + address + '\'' +
                ", numtel='" + phone + '\'' +
                '}';
    }
}
