package com.medireportui.medireportui.proxies;

import com.medireportui.medireportui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@FeignClient(name = "microservice-patients", url = "localhost:9190")
@FeignClient(name = "microservice-patients", url = "${SPRING.PATIENT.URL}")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> listerLesPatients();

 /*   @GetMapping( value = "/patient/get")
    String showRecupererUnPatient();*/

    @GetMapping( value = "/patient/getOne")
    PatientBean recupererUnPatient(@RequestParam int id);


    @PostMapping(path = "/patient/add")
    PatientBean ajouterUnPatient(@RequestBody PatientBean patient);



    @GetMapping( value = "/patient/update")
    PatientBean modifierUnPatientGet(@RequestParam("id") int id);

    @PutMapping( value = "/patient/update")
    PatientBean modifierUnPatient(@RequestBody PatientBean patient);


    @GetMapping( value = "/patient/delete")
    void effacerUnPatientGet();

    @PostMapping( value = "/patient/delete")
    void effacerUnPatient(@RequestParam int id);

}
