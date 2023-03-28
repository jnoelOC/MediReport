package com.medipatient.medipatient.web.controller;

import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.repository.IPatientRepository;
import com.medipatient.medipatient.web.exceptions.PatientIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
public class PatientController {

    @Autowired
    private IPatientRepository patientDao;


    @GetMapping("/patients")
    public List<Patient> listerTousLesPatients() {
       /* Patient patient = new Patient();
        patient.setAdressepostale("toto");
        patient.setNom("toto");
        patient.setPrenom("Tutu");
        patient.setDatenaiss(LocalDate.now());
        patientDao.save(patient);*/
        List<Patient> patients = patientDao.findAll();

        if(patients.isEmpty()) throw new PatientIntrouvableException("Aucun patient n'est disponible.");

        return patients;
    }

    @GetMapping( value = "/patient/{id}")
    public Optional<Patient> recupererUnPatient(@PathVariable int id) {

        Optional<Patient> patient = patientDao.findById(id);

        if(!patient.isPresent()) throw new PatientIntrouvableException("Le patient correspondant à l'id " + id + " n'existe pas.");

        return patient;
    }


    @PutMapping(value = "/patient")
    public ResponseEntity<Patient> ajouterUnPatient(@RequestBody Patient patient) {
        Patient patientAdded = patientDao.save(patient);
        if (Objects.isNull(patientAdded)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patientAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping( value = "/patient/{id}")
    public ResponseEntity<Patient> effacerUnPatient(@PathVariable int id) {

        patientDao.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
