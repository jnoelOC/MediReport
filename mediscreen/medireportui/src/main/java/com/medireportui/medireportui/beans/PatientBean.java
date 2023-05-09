package com.medireportui.medireportui.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class PatientBean {
    private Integer id;
    private String firstname;
    private String lastname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String sex;
    private String address;
    private String phone;

    public PatientBean() {
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
        return "PatientBean{" +
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
