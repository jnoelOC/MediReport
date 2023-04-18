package com.medipatient.medipatient.web.controller;

import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.repository.IPatientRepository;
import com.medipatient.medipatient.service.PatientService;
import com.medipatient.medipatient.web.exceptions.PatientIntrouvableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private static Logger logger = LoggerFactory.getLogger(PatientController.class);

    @GetMapping("/patients")
    public List<Patient> listerTousLesPatients() {
        logger.info("dans la méthode listerTousLesPatients de medipatient");
        List<Patient> patients = patientService.findAll();

        if(patients.isEmpty()){
            logger.error("Erreur dans listerTousLesPatients de medipatient : status Non trouvé.");
            return patients;
        }
        else{
            logger.info("status patients trouvés.");
            return patients;
        }
//        if(patients.isEmpty()) throw new PatientIntrouvableException("Aucun patient n'est disponible.");
//        return patients;
    }

    @GetMapping( value = "/patient")
    public Optional<Patient> recupererUnPatient(@RequestParam int id) {
        logger.info("dans la méthode recupererUnPatient de medipatient");
        Optional<Patient> patient = patientService.findById(id);

        if (patient.isPresent()) {
            logger.info("status patient trouvé.");
            return patient;

        } else {
            logger.error("Erreur dans recupererUnPatient de medipatient : status Non trouvé.");
            return Optional.empty();
        }


//        if(!patient.isPresent()) throw new PatientIntrouvableException("Le patient correspondant à l'id " + id + " n'existe pas.");
//        return patient;
    }


    @PostMapping(value = "/patient/add")
    public ResponseEntity<Patient> ajouterUnPatient(@RequestBody Patient patient) {
        logger.info("dans la méthode ajouterUnPatient");
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
        logger.info("dans la méthode effacerUnPatient");
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
