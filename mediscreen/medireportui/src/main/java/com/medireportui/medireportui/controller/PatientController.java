package com.medireportui.medireportui.controller;

import com.medireportui.medireportui.beans.PatientBean;
import com.medireportui.medireportui.proxies.MicroservicePatientsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final MicroservicePatientsProxy patientsProxy;

    public PatientController(MicroservicePatientsProxy patientsProxy){
        this.patientsProxy = patientsProxy;
    }

    @GetMapping({"/", "/home"})
    public String getHome() {
        logger.info("Je suis dans home de medireportui");
        return "home";
    }

    @GetMapping("/patient/list")
//    public ResponseEntity<List<PatientBean>> listOfPatients(Model model) {
    public String listOfPatients(Model model) {
        logger.info("Je suis dans listOfPatients de medireportui");
        List<PatientBean> patients =  patientsProxy.listerLesPatients();

        if (null == patients || patients.isEmpty()){
            logger.info("liste des patients null ou vide !");
//            return new ResponseEntity<>(patients, HttpStatus.NOT_FOUND);
            model.addAttribute("errorMsg", "This list is empty.");
        }
        else{
            logger.info("status patients trouvés.");
//            return new ResponseEntity<>(patients, HttpStatus.FOUND);
            model.addAttribute("patients", patients);
        }


        return "patient/list";
    }

    @GetMapping("/patient/get")
    public ResponseEntity<PatientBean> recupererUnPatient(@RequestParam int id) {
        logger.info("Je suis dans recupererUnPatient de medireportui");
        PatientBean patient = patientsProxy.recupererUnPatient(id);

        if (null == patient) {
            logger.error("Erreur dans recupererUnPatient de medireportui : status Non trouvé.");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            logger.info("status patient trouvé.");
            return new ResponseEntity<>(patient, HttpStatus.FOUND);
        }
    }

    @PostMapping("/patient/add")
    public String ajouterUnPatient(Model model, @RequestBody PatientBean patient) {
        logger.info("Je suis dans ajouterUnPatient de medireportui");
        PatientBean patient1 = patientsProxy.ajouterUnPatient(patient);

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);

        return "patient/list";
    }

    @DeleteMapping("/patient/delete")
    public String effacerUnPatient(Model model, @RequestParam int id) {
        logger.info("Je suis dans effacerUnPatient de medireportui");
        patientsProxy.effacerUnPatient(id);

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);
        return "patient/list";
    }
}
