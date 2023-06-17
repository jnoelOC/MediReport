package com.medireportui.medireportui.proxies;

import com.medireportui.medireportui.beans.NoteBean;
import com.medireportui.medireportui.beans.PatientBean;
import org.bson.types.ObjectId;
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

    @GetMapping(value = "/note/listby")
    List<NoteBean> listerLesNotesParPatient(@RequestParam int idPatient);

    @PostMapping(path = "/note/add")
    NoteBean ajouterUneNote(@RequestBody NoteBean note);


    @GetMapping( value = "/note/update")
    NoteBean modifierUneNoteGet(@RequestParam String id);

    @PostMapping( value = "/note/update")
    NoteBean modifierUneNote(@RequestBody NoteBean note, @RequestParam int idPatient);

    @DeleteMapping(value = "/note/delete")
    void effacerUneNote(@RequestParam String id, @RequestParam int idPatient);

    /*
    @GetMapping( value = "/patient/get/{id}")
    PatientBean recupererUnPatient(@PathVariable("id") Integer id);
*/

}