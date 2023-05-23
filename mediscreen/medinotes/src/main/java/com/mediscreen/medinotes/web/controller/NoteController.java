package com.mediscreen.medinotes.web.controller;


import com.mediscreen.medinotes.model.Note;
import com.mediscreen.medinotes.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            return notes;
        }
        else{
            logger.info("status notes trouvées.");
            return notes;
        }
    }

}
