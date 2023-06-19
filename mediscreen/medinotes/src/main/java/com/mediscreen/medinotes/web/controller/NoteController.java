package com.mediscreen.medinotes.web.controller;


import com.mediscreen.medinotes.model.Note;
import com.mediscreen.medinotes.service.NoteService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;
    private static Logger logger = LoggerFactory.getLogger(NoteController.class);

    @GetMapping("/notes")
    public List<Note> listerToutesLesNotes() {
        logger.info("dans la méthode listerToutesLesNotes de medinotes");
        List<Note> notes = noteService.findAll();

        if(notes.isEmpty()){
            logger.error("Erreur dans listerToutesLesNotes de medinotes : status Non trouvé.");
        }
        else{
            logger.info("status notes trouvées.");
        }
        return notes;
    }

    @GetMapping("/note/listby")
    public List<Note> listerToutesLesNotesParPatient(@RequestParam("idPatient") Integer idPatient) {
        logger.info("dans la méthode listerToutesLesNotesParPatient de medinotes");

        List<Note> notes = noteService.listerLesNotesParPatient(idPatient);

        if(notes.isEmpty()){
            logger.error("Erreur dans listerToutesLesNotes de medinotes : status Non trouvé.");
        }
        else{
            logger.info("status notes trouvées.");
        }
        return notes;
    }


    @PostMapping(value = "/note/add")
    public ResponseEntity<Note> ajouterUneNote(@RequestBody Note note) {
        logger.info("je suis dans la méthode ajouterUneNote de medinote");
        Note noteAdded = noteService.save(note);

        if (Objects.isNull(noteAdded)) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(noteAdded, HttpStatus.CREATED);
    }

    @GetMapping(value = "/note/update")
    public ResponseEntity<Note> modifierUneNoteGet(@RequestParam String id) {
        logger.info("dans la méthode modifierUneNoteGet");

        Optional<Note> note = noteService.findById(id);
        if (note.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(note.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value = "/note/update")
    public ResponseEntity<Note> modifierUneNote(@RequestBody Note note) {
        logger.info("dans la méthode modifierUneNote");

        Note  noteUpdated = noteService.update(note);

        if (Objects.isNull(noteUpdated)) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(noteUpdated, HttpStatus.OK);
    }

    @DeleteMapping( value = "/note/delete")
    public ResponseEntity<HttpStatus> effacerUneNote(@RequestParam String id) {
        logger.info("dans la méthode effacerUneNote");

        if (null!=id) {
            noteService.deleteById(id);
            return ResponseEntity.ok().build();}
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
