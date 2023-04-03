package com.medipatient.medipatient.web.controller;

import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.repository.IPatientRepository;
import com.medipatient.medipatient.service.PatientService;
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
    private PatientService patientService;


    @GetMapping("/patients")
    public List<Patient> listerTousLesPatients() {
       /* Patient patient = new Patient();
        patient.setAdressepostale("toto");
        patient.setNom("toto");
        patient.setPrenom("Tutu");
        patient.setDatenaiss(LocalDate.now());
        patientDao.save(patient);*/
        List<Patient> patients = patientService.findAll();

        if(patients.isEmpty()) throw new PatientIntrouvableException("Aucun patient n'est disponible.");

        return patients;
    }

    @GetMapping( value = "/patient")
    public Optional<Patient> recupererUnPatient(@RequestParam int id) {

        Optional<Patient> patient = patientService.findById(id);

        if(!patient.isPresent()) throw new PatientIntrouvableException("Le patient correspondant à l'id " + id + " n'existe pas.");

        return patient;
    }


    @PostMapping(value = "/patient/add")
    public ResponseEntity<Patient> ajouterUnPatient(@RequestBody Patient patient) {
        Patient patientAdded = patientService.save(patient);
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


    @DeleteMapping( value = "/patient/delete")
    public ResponseEntity<String> effacerUnPatient(@RequestParam int id) {

        Boolean idFound = false;
        List<Patient> patients = patientService.findAll();
        for (Patient patient: patients) {
            if(patient.getId().equals(id)){
                idFound = true;
                break;
            }
        }
        if(idFound) {
            patientService.deleteById(id);
            return ResponseEntity.ok().build();}
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
