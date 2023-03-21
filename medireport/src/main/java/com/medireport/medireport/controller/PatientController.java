package com.medireport.medireport.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @GetMapping("/patient")
    public ResponseEntity<String> listOfPatients() {

        return ResponseEntity.ok("vive les patients !");
    }
}
