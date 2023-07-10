package com.medireport.medireport.proxies;

import com.medireport.medireport.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@FeignClient(name = "microservice-notes", url = "localhost:9290")
@FeignClient(name = "microservice-notes", url = "${SPRING.NOTE.URL}")
public interface MicroserviceNotesProxy {

/*
    @GetMapping(value = "/notes")
    List<NoteBean> listerLesNotes();
*/

    @GetMapping(value = "/note/listbypat")
    List<NoteBean> listerLesNotesParPatient(@RequestParam("id") int idPatient);


    /*
    @GetMapping( value = "/patient/get/{id}")
    PatientBean recupererUnPatient(@PathVariable("id") Integer id);
*/

}