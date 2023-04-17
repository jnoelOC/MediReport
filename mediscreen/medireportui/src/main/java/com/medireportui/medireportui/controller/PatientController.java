package com.medireportui.medireportui.controller;

import com.medireportui.medireportui.beans.PatientBean;
import com.medireportui.medireportui.proxies.MicroservicePatientsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {

    private final MicroservicePatientsProxy patientsProxy;

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);
    public PatientController(MicroservicePatientsProxy patientsProxy){
        this.patientsProxy = patientsProxy;
    }

    @GetMapping("/patients")
    public String listOfPatients(Model model) {
        logger.info("Je suis dans listOfPatients de medireportui");
        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        for(PatientBean pat : patients)
        {
            logger.info(pat.getFirstname());
        }
        model.addAttribute("patients", patients);

        return "patient/list";
    }

    @GetMapping("/patient")
    public String recupererUnPatient(Model model, @RequestParam int id) {
        logger.info("Je suis dans recupererUnPatient de medireportui");
        PatientBean patient = patientsProxy.recupererUnPatient(id);

        model.addAttribute("patient", patient);

        return "patient";
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
