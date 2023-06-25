package com.medireport.medireport.proxies;

import com.medireport.medireport.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "microservice-patients", url = "localhost:9190")
@FeignClient(name = "microservice-patients", url = "${SPRING.PATIENT.URL}")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> listerLesPatients();

 /*   @GetMapping( value = "/patient/get")
    String showRecupererUnPatient();*/

    @GetMapping( value = "/patient/getOne")
    PatientBean recupererUnPatient(@RequestParam int id);

}
