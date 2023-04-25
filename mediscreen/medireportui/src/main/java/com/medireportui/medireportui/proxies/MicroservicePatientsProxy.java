package com.medireportui.medireportui.proxies;

import com.medireportui.medireportui.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@FeignClient(name = "microservice-patients", url = "localhost:9190")
@FeignClient(name = "microservice-patients", url = "${SPRING.PATIENT.URL}")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> listerLesPatients();

    @GetMapping( value = "/patient/get/{id}")
    PatientBean recupererUnPatient(@PathVariable("id") Integer id);

    @PostMapping( value = "/patient/add")
    PatientBean ajouterUnPatient();

    @PostMapping( value = "/patient/validate")
    PatientBean ajouterUnPatient(@RequestParam String firstname, @RequestParam String lastname, @RequestParam LocalDate dob,
                                 @RequestParam String sex, @RequestParam String address, @RequestParam String phone);


    @GetMapping( value = "/patient/update")
    PatientBean modifierUnPatientGet(@RequestParam Integer id);

    @PutMapping( value = "/patient/update")
    PatientBean modifierUnPatient(@RequestParam Integer id, @RequestParam String firstname, @RequestParam String lastname,
                                  @RequestParam LocalDate dob, @RequestParam String sex, @RequestParam String address,
                                  @RequestParam String phone);


    @GetMapping( value = "/patient/delete")
    void effacerUnPatientGet();

    @PostMapping( value = "/patient/delete")
    void effacerUnPatient(@RequestParam Integer id);

}
