package com.medireportui.medireportui.controller;

import com.medireportui.medireportui.beans.PatientBean;
import com.medireportui.medireportui.proxies.MicroservicePatientsProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {

    private final MicroservicePatientsProxy patientsProxy;

    public PatientController(MicroservicePatientsProxy patientsProxy){
        this.patientsProxy = patientsProxy;
    }

    @GetMapping("/patients")
    public String listOfPatients(Model model) {
        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);

        return "patient/list";
    }

    @GetMapping("/patient/{id}")
    public String recupererUnPatient(Model model, @PathVariable int id) {
        PatientBean patient = patientsProxy.recupererUnPatient(id);
        model.addAttribute("patient", patient);

        return "patient";
    }

    @PutMapping("/patient")
    public String ajouterUnPatient(Model model, @RequestBody PatientBean patient) {
        PatientBean patient1 = patientsProxy.ajouterUnPatient(patient);

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);

        return "patient/list";
    }

    @DeleteMapping("/patient/{id}")
    public String effacerUnPatient(Model model, @PathVariable int id) {
        patientsProxy.effacerUnPatient(id);

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);
        return "patient/list";
    }
}
