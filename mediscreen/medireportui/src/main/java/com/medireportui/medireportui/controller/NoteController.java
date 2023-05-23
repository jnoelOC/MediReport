package com.medireportui.medireportui.controller;

import com.medireportui.medireportui.beans.NoteBean;
import com.medireportui.medireportui.proxies.MicroserviceNotesProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NoteController {

    private static Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final MicroserviceNotesProxy notesProxy;
    public NoteController(MicroserviceNotesProxy notesProxy){
        this.notesProxy = notesProxy;
    }

    @GetMapping("/note/list")
//    public ResponseEntity<List<PatientBean>> listOfPatients(Model model) {
    public String listOfNotes(Model model) {
        logger.info("Je suis dans listOfNotes de medireportui");
        List<NoteBean> notes =  notesProxy.listerLesNotes();

        if (null == notes || notes.isEmpty()){
            logger.info("liste des notes null ou vide !");
//            return new ResponseEntity<>(patients, HttpStatus.NOT_FOUND);
            model.addAttribute("errorMsg", "This list is empty.");
        }
        else{
            logger.info("status notes trouvés.");
//            return new ResponseEntity<>(patients, HttpStatus.FOUND);
            model.addAttribute("notes", notes);
        }
        return "note/list";
    }
}
