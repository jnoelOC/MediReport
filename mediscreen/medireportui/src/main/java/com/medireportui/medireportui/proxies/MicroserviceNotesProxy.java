package com.medireportui.medireportui.proxies;

import com.medireportui.medireportui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@FeignClient(name = "microservice-notes", url = "localhost:9290")
@FeignClient(name = "microservice-notes", url = "${SPRING.NOTE.URL}")
public interface MicroserviceNotesProxy {

    @GetMapping(value = "/notes")
    List<NoteBean> listerLesNotes();




/*
    @GetMapping( value = "/patient/get/{id}")
    PatientBean recupererUnPatient(@PathVariable("id") Integer id);


    @PostMapping(path = "/patient/add")
    PatientBean ajouterUnPatient(@RequestBody PatientBean patient);



    @GetMapping( value = "/patient/update")
    PatientBean modifierUnPatientGet(@RequestParam Integer id);

    @PutMapping( value = "/patient/update")
    PatientBean modifierUnPatient(@RequestBody PatientBean patient);


    @GetMapping( value = "/patient/delete")
    void effacerUnPatientGet();

    @PostMapping( value = "/patient/delete")
    void effacerUnPatient(@RequestParam Integer id);
*/
}