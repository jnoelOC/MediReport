package com.medipatient.medipatient.web.controller;

import com.medipatient.medipatient.dto.PatientInfoDTO;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.PatientService;
//import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    }

    @GetMapping( value = "/patient/getOne")
    public Patient recupererUnPatient(@RequestParam int id) {
        logger.info("dans la méthode recupererUnPatient de medipatient");
        Optional<Patient> patient;
        if(id > 0) {
            patient = patientService.findById(id);
            if (patient.isPresent()) {
                logger.info("status patient trouvé.");
                return patient.get();
               // return new ResponseEntity<>(patient.get(), HttpStatus.FOUND);

            } else {
                logger.error("Erreur dans recupererUnPatient de medipatient : status Non trouvé.");
                return null;
                //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else {
            logger.error("Erreur dans recupererUnPatient de medipatient : idPatient <= 0.");
            return  null;
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

/*    @PostMapping(value = "/patient/adddto")
    //  public ResponseEntity<Patient>
    public ResponseEntity<PatientInfoDTO> ajouterUnPatientDto(@RequestBody Patient patient) {
        logger.info("dans la méthode ajouterUnPatientDto");

        PatientInfoDTO patientDto = new PatientInfoDTO();


        PatientInfoDTO patientDtoAdded = patientService.addPatient(patientDto);

        if (Objects.isNull(patientDtoAdded)) {
            return ResponseEntity.noContent().build();
            //return null;
        }
/*        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patientAdded.getId())
                .toUri();*/
        // return ResponseEntity.created(location).build();
/*         return new ResponseEntity<>(patientDtoAdded, HttpStatus.CREATED);
        //return patientDtoAdded;
    }
*/
    // @PostMapping
    //    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {
    //
    //        LOGGER.info("save patient :{} {} request", patient.getFirstName(), patient.getLastName());
    //        return new ResponseEntity<>(patientService.savePatient(patient), CREATED);
    //    }

    // @PostMapping(value = "/patient/validate")
    @PostMapping(value = "/patient/add")
    public ResponseEntity<Patient> ajouterUnPatient(@RequestBody Patient patient) {
        logger.info("je suis dans la méthode ajouterUnPatient de medipatient");
        Patient patientAdded = patientService.save(patient);

        if (Objects.isNull(patientAdded)) {
            return ResponseEntity.noContent().build();
           // return null;
        }
/*        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patientAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();*/
        return new ResponseEntity<>(patientAdded, HttpStatus.CREATED);
       // return patientAdded;
    }

    @GetMapping(value = "/patient/update")
//    public ResponseEntity<Patient> modifierUnPatientGet(@RequestParam("id") int id) {
    public Patient modifierUnPatientGet(@RequestParam("id") int id) {

        logger.info("dans la méthode modifierUnPatientGet");

        if(id<=0 || id > patientService.findAll().size()){
            return null;
        }

        Optional<Patient> patient = patientService.findById(id);
        if(patient.isEmpty()) {
            logger.info("dans la méthode modifierUnPatientGet avec patient à null");
           //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return null;
        }
        else {
            logger.info("dans la méthode modifierUnPatientGet avec une valeur à patient");
            //return new ResponseEntity<>(patient.get(), HttpStatus.FOUND);
            return patient.get();
        }
    }

    @PutMapping(value = "/patient/update")
    public ResponseEntity<Patient> modifierUnPatient(@RequestBody Patient patient) {
        logger.info("dans la méthode modifierUnPatient");

        Patient  patientUpdated = patientService.save(patient);

        if (Objects.isNull(patientUpdated)) {
            return ResponseEntity.noContent().build();
          //  return null;
        }
/*        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patientUpdated.getId())
                .toUri();
         return ResponseEntity.ok(location)..build();*/
         return new ResponseEntity<>(patientUpdated, HttpStatus.OK);
        //return patientUpdated;
    }


    @PostMapping( value = "/patient/delete")
    public ResponseEntity<HttpStatus> effacerUnPatient(@RequestParam int id) {
        logger.info("dans la méthode effacerUnPatient");
        boolean idFound = false;
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
