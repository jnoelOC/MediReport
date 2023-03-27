package com.medireport.medireport.controller;

import com.medireport.medireport.beans.PatientBean;
import com.medireport.medireport.proxies.MicroservicePatientsProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
