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

    @GetMapping("/patient")
    public String recupererUnPatient(Model model, @RequestParam int id) {
        PatientBean patient = patientsProxy.recupererUnPatient(id);
        model.addAttribute("patient", patient);

        return "patient";
    }

    @PostMapping("/patient/add")
    public String ajouterUnPatient(Model model, @RequestBody PatientBean patient) {
        PatientBean patient1 = patientsProxy.ajouterUnPatient(patient);

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);

        return "patient/list";
    }

    @DeleteMapping("/patient/delete")
    public String effacerUnPatient(Model model, @RequestParam int id) {
        patientsProxy.effacerUnPatient(id);

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);
        return "patient/list";
    }
}
