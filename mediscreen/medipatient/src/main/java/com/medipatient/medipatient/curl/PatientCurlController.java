package com.medipatient.medipatient.curl;

import com.medipatient.medipatient.dto.PatientInfoDTO;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.PatientService;
//import com.medireportui.medireportui.beans.PatientBean;
//import com.medireportui.medireportui.proxies.MicroservicePatientsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
public class PatientCurlController {
    private static Logger logger = LoggerFactory.getLogger(PatientCurlController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient/curl/patients")
    public ResponseEntity<List<Patient>> listerTousLesPatients() {
        logger.info("dans la méthode listerTousLesPatients de medipatient");
        List<Patient> patients = patientService.findAll();

        if(patients.isEmpty()){
            logger.error("Erreur dans listerTousLesPatientsCurl de medipatient : status Non trouvé.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            logger.info("status liste de patients trouvés.");
            return new ResponseEntity<>(patients, HttpStatus.FOUND);
        }
    }


    @PostMapping(value = "/patient/curl/add")
    public ResponseEntity<PatientInfoDTO> ajouterUnPatient(@RequestBody @ModelAttribute("patientDto") PatientInfoDTO patientDto) {
        logger.info("je suis dans la méthode ajouterUnPatientDTOCurl de medipatient");
        PatientInfoDTO patientDtoAdded = patientService.addPatient(patientDto);

        if (Objects.isNull(patientDtoAdded)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<>(patientDtoAdded, HttpStatus.FOUND);
    }
}
