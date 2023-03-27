package com.medireport.medireport.beans;

import java.time.LocalDate;
import java.util.Date;

public class PatientBean {
    private Long id;
    private String prenom;
    private String nom;
    private LocalDate datenaiss;
    private Boolean genre;
    private String adressepostale;
    private String numtel;

    public PatientBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(LocalDate datenaiss) {
        this.datenaiss = datenaiss;
    }

    public Boolean getGenre() {
        return genre;
    }

    public void setGenre(Boolean genre) {
        this.genre = genre;
    }

    public String getAdressepostale() {
        return adressepostale;
    }

    public void setAdressepostale(String adressepostale) {
        this.adressepostale = adressepostale;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "PatientBean{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", datenaiss=" + datenaiss +
                ", genre=" + genre +
                ", adressepostale='" + adressepostale + '\'' +
                ", numtel='" + numtel + '\'' +
                '}';
    }
}
