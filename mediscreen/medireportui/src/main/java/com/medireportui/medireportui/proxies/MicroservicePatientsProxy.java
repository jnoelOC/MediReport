package com.medireportui.medireportui.proxies;

import com.medireportui.medireportui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "microservice-patients", url = "localhost:9190")
@FeignClient(name = "microservice-patients", url = "${SPRING.PATIENT.URL}")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> listerLesPatients();

    @GetMapping( value = "/patient")
    PatientBean recupererUnPatient(@RequestParam Integer id);

    @PostMapping( value = "/patient/add")
    PatientBean ajouterUnPatient(@RequestBody PatientBean patient);

    @DeleteMapping( value = "/patient/delete")
    void effacerUnPatient(@RequestParam Integer id);

}
