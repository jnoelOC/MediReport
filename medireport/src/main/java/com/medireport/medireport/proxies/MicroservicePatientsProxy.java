package com.medireport.medireport.proxies;

import com.medireport.medireport.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-patients", url = "localhost:9190")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/patients")
    List<PatientBean> listerLesPatients();

    @GetMapping( value = "/Produits/{id}")
    PatientBean recupererUnProduit(@PathVariable("id") int id);

}
