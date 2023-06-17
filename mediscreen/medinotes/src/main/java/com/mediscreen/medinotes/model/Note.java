package com.mediscreen.medinotes.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.PrePersist;
import java.util.UUID;


@Document(collection = "medinotes")
public class Note {
    @Id
    private String id;
    private String name;
    private String note;
    private Integer idPatient;


    public Note(String id, String name, String note, Integer idPatient) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.idPatient = idPatient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }
}
