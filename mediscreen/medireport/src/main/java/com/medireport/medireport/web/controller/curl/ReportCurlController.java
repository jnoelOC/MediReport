package com.medireport.medireport.web.controller.curl;

import com.medireport.medireport.beans.NoteBean;
import com.medireport.medireport.beans.PatientBean;
import com.medireport.medireport.model.Disease;
import com.medireport.medireport.proxies.MicroserviceNotesProxy;
import com.medireport.medireport.proxies.MicroservicePatientsProxy;
import com.medireport.medireport.service.DiseaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class ReportCurlController {
    private static Logger logger = LoggerFactory.getLogger(ReportCurlController.class);

    @Autowired
    private DiseaseService diseaseService;

    private final MicroserviceNotesProxy notesProxy;
    private final MicroservicePatientsProxy patientsProxy;

    public ReportCurlController(MicroserviceNotesProxy notesProxy, MicroservicePatientsProxy patientsProxy){
        this.notesProxy = notesProxy;
        this.patientsProxy = patientsProxy;
    }

    @PostMapping("/report/curl/assess/id")
    public ResponseEntity<String> recupererNiveauDeRisqueDuPatientParId(Integer idPatient) {
        logger.info("dans la méthode recupererNiveauDeRisqueDuPatientParId de medireport");

        PatientBean patient = patientsProxy.recupererUnPatient(idPatient);
        if(null == patient){
            logger.error("Dans recupererNiveauDeRisqueDuPatientParId de medireport : patient Non trouvé.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        }

        List<NoteBean> notes = notesProxy.listerLesNotesParPatient(idPatient);

        Disease.RiskLevel riskLevel = diseaseService.getRiskLevel(notes, patient);

        if(null == riskLevel){
            logger.error("Dans recupererNiveauDeRisqueDuPatientParId de medireport : riskLevel Non trouvé.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        }
        else{
            logger.info("Dans recupererNiveauDeRisqueDuPatientParId, riskLevel trouvé.");
            String result = " Patient : " + patient.getFirstname() +
                    " (age=" + LocalDate.now().minusYears(patient.getDob().getYear()).getYear() +
                    ") diabetes assessment is : " + riskLevel.toString();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
    }

    @PostMapping("/report/curl/assess/familyName")
    public ResponseEntity<String> recupererNiveauDeRisqueParNom(String familyName) {
        logger.info("dans la méthode recupererNiveauDeRisqueParNom de medireport");
        PatientBean patient = null;
        Disease.RiskLevel riskLevel = null;

        Optional<Integer> idPatientOptional = patientsProxy.listerLesPatients().stream()
                .filter(onePatient -> onePatient.getFirstname().equals(familyName))
                .map(PatientBean::getId)
                .findFirst();

        Integer idPatient = idPatientOptional.orElse(null);

        if (null != idPatient) {
            patient = patientsProxy.recupererUnPatient(idPatient);
            if (null == patient) {
                logger.error("Dans recupererNiveauDeRisqueParNom de medireport : patient Non trouvé.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
            }

            List<NoteBean> notes = notesProxy.listerLesNotesParPatient(idPatient);

            riskLevel = diseaseService.getRiskLevel(notes, patient);
        }

        if(null == riskLevel){
            logger.error("Dans recupererNiveauDeRisqueParNom de medireport : riskLevel Non trouvé.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        }
        else{
            logger.info("Dans recupererNiveauDeRisqueParNom, riskLevel trouvé.");
            String result = " Patient : " + patient.getFirstname() +
                    " (age=" + LocalDate.now().minusYears(patient.getDob().getYear()).getYear() +
                    ") diabetes assessment is : " + riskLevel.toString();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
    }


    @GetMapping("/report/curl/get")
    public ResponseEntity<String> recupererNiveauDeRisqueDuPatient(Integer idPatient) {
        logger.info("dans la méthode recupererNiveauDeRisqueDuPatient de medireport");

        PatientBean patient = patientsProxy.recupererUnPatient(idPatient);
        if(null == patient){
            logger.error("Dans recupererNiveauDeRisqueDuPatient de medireport : patient Non trouvé.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        }

        List<NoteBean> notes = notesProxy.listerLesNotesParPatient(idPatient);

        Disease.RiskLevel riskLevel = diseaseService.getRiskLevel(notes, patient);

        if(null == riskLevel){
            logger.error("Dans recupererNiveauDeRisqueDuPatient de medireport : riskLevel Non trouvé.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        }
        else{
            logger.info("Dans recupererNiveauDeRisqueDuPatient, riskLevel trouvé.");
            String result = " Patient : " + patient.getFirstname() +
                    " (age=" + LocalDate.now().minusYears(patient.getDob().getYear()).getYear() +
                    ") diabetes assessment is : " + riskLevel.toString();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
    }


    @GetMapping("/report/curl/getByName")
    public ResponseEntity<String> recupererNiveauDeRisqueParNomDuPatient(String familyName) {
        logger.info("dans la méthode recupererNiveauDeRisqueDuPatient de medireport");
        PatientBean patient = null;
        Disease.RiskLevel riskLevel = null;

        Optional<Integer> idPatientOptional = patientsProxy.listerLesPatients().stream()
                .filter(onePatient -> onePatient.getFirstname().equals(familyName))
                .map(PatientBean::getId)
                .findFirst();

        Integer idPatient = idPatientOptional.orElse(null);

        if (null != idPatient) {
            patient = patientsProxy.recupererUnPatient(idPatient);
            if (null == patient) {
                logger.error("Dans recupererNiveauDeRisqueDuPatient de medireport : patient Non trouvé.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
            }

            List<NoteBean> notes = notesProxy.listerLesNotesParPatient(idPatient);

            riskLevel = diseaseService.getRiskLevel(notes, patient);
        }

        if(null == riskLevel){
            logger.error("Dans recupererNiveauDeRisqueDuPatient de medireport : riskLevel Non trouvé.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
        }
        else{
            logger.info("Dans recupererNiveauDeRisqueDuPatient, riskLevel trouvé.");
            String result = " Patient : " + patient.getFirstname() +
                    " (age=" + LocalDate.now().minusYears(patient.getDob().getYear()).getYear() +
                    ") diabetes assessment is : " + riskLevel.toString();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
    }
}
