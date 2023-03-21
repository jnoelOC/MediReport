package com.medipatient.medipatient.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", unique=true, nullable=false)
    private Long id;

    @Column(name="PRENOM", length=50)
    private String prenom;

    @Column(name="NOM", length=50)
    private String nom;

    @Column(name="DATENAISS")
    private Date datenaiss;

    @Column(name="GENRE")
    private Boolean genre;

    @Column(name="ADRESSEPOSTALE", length=250)
    private String adressepostale;

    @Column(name="NUMTEL", length=20)
    private String numtel;

    public Patient() {
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

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
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
        return "Patient{" +
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
