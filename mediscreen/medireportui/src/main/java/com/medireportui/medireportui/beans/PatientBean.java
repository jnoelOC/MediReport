package com.medireportui.medireportui.beans;

import java.time.LocalDate;
import java.util.Date;

public class PatientBean {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate dob;
    private Boolean sex;
    private String address;
    private String phone;

    public PatientBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
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
