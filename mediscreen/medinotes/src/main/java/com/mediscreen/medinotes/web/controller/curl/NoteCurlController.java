package com.mediscreen.medinotes.web.controller.curl;

import com.mediscreen.medinotes.model.Note;
import com.mediscreen.medinotes.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class NoteCurlController {

    private static Logger logger = LoggerFactory.getLogger(NoteCurlController.class);

    @Autowired
    private NoteService noteService;

    @GetMapping("/note/curl/notes")
    public ResponseEntity<List<Note>> listerToutesLesNotess() {
        logger.info("dans la méthode listerTousLesPatients de medipatient");
        List<Note> notes = noteService.findAll();

        if(notes.isEmpty()){
            logger.error("Dans listerToutesLesNotesCurl de medinotes : status Non trouvé.");
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        else{
            logger.info("Dans liste de notes trouvées.");
            return new ResponseEntity<>(notes, HttpStatus.FOUND);
        }
    }


    @PostMapping(value = "/note/curl/add")
    public ResponseEntity<Note> ajouterUneNote(@RequestBody @ModelAttribute("note") Note note, String noteName, int idPatient) {
        logger.info("je suis dans la méthode ajouterUneNoteCurl de medinotes");
        note.setIdPatient(idPatient);
        note.setName(noteName);
        Note noteAdded = noteService.save(note);

        if (Objects.isNull(noteAdded)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(noteAdded, HttpStatus.FOUND);
    }
}
