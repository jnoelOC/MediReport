package com.medireportui.medireportui.proxies;

import com.medireportui.medireportui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-patients", url = "localhost:9190")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> listerLesPatients();

    @GetMapping( value = "/patient/{id}")
    PatientBean recupererUnPatient(@PathVariable("id") Integer id);

    @PutMapping( value = "/patient")
    PatientBean ajouterUnPatient(@RequestBody PatientBean patient);

    @DeleteMapping( value = "/patient/{id}")
    void effacerUnPatient(@PathVariable("id") Integer id);

}
