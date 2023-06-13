package com.medireportui.medireportui.controller;

import com.medireportui.medireportui.beans.NoteBean;
import com.medireportui.medireportui.beans.PatientBean;
import com.medireportui.medireportui.proxies.MicroserviceNotesProxy;
import com.medireportui.medireportui.proxies.MicroservicePatientsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class NoteController {

    private static Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final MicroserviceNotesProxy notesProxy;
    private final MicroservicePatientsProxy patientsProxy;
    public NoteController(MicroserviceNotesProxy notesProxy, MicroservicePatientsProxy patientsProxy){
        this.notesProxy = notesProxy;
        this.patientsProxy = patientsProxy;
    }


    @GetMapping("/note/listby")
    public String listOfNotes(Model model, @RequestParam int idPatient) {
        logger.info("Je suis dans listOfNotes de medireportui");
        List<NoteBean> notes =  notesProxy.listerLesNotesParPatient(idPatient);

        if (null == notes || notes.isEmpty()){
            logger.info("liste des notes null ou vide !");
            model.addAttribute("errorMsg", "This list is empty.");
        }
        else{
            logger.info("status notes trouvées.");
            model.addAttribute("notes", notes);
        }
        return "note/listby";
    }


    @GetMapping(value = "/note/add/{id}")
    public String ajouterUneNoteGet(Model model, @PathVariable("id") int idPatient) {
        logger.info("Je suis dans ajouterUneNoteGet de medireportui");

        model.addAttribute("note", new NoteBean());

        PatientBean patient = patientsProxy.recupererUnPatient(idPatient);
        model.addAttribute("patient", patient);

        return "note/add";
    }


    @PostMapping( value = "/note/validate")
    public String ajouterUneNote(@RequestBody @ModelAttribute("note") @Valid NoteBean note, Model model, @RequestParam("idPatient") int idPatient) {

        logger.info("Je suis dans ajouterUneNotePost de medireportui");
       /* if (null == note){
            model.addAttribute("errorMsg", "note object is null.");
            return "note/add";
        }*/

     /*   if (null == note.getId()){
            model.addAttribute("errorMsgId", "Id is null.");
            return "note/add";
        }
*/
        if(note.getName().isBlank()){
            model.addAttribute("errorMsgName", "Name is mandatory.");
            return "note/add";
        }

        if(note.getNote().isBlank()){
            model.addAttribute("errorMsgNote", "Note is mandatory.");
            return "note/add";
        }

        if(note.getIdPatient() <= 0){
            model.addAttribute("errorMsgIdPatient", "IdPatient is mandatory.");
            return "note/add";
        }

        NoteBean note1 = notesProxy.ajouterUneNote(note);
        if (null == note1) {
            model.addAttribute("errorMsg", "note object is null.");
            return "note/add";
        }

        PatientBean patient = patientsProxy.recupererUnPatient(idPatient);
        model.addAttribute("patient", patient);

        List<NoteBean> notes =  notesProxy.listerLesNotesParPatient(idPatient);
        model.addAttribute("notes", notes);
        return "note/listby";
    }


    @GetMapping("/note/update")
    public String updateNoteGet(@RequestParam("id") String id, Model model, @RequestParam("idPatient") int idPatient) {
        logger.info("Je suis dans updateNoteGet de medireportui");
        NoteBean n = notesProxy.modifierUneNoteGet(id);
        model.addAttribute("note", n);

        return "note/update";
    }

    @PostMapping("/note/update")
    public String updateNotePost(Model model, @RequestBody  @ModelAttribute("note") @Valid NoteBean note, @RequestParam("idPatient") int idPatient){
        logger.info("Je suis dans updateNotePost de medireportui");

        if (null == note){
            model.addAttribute("errorMsg", "note object is null.");
            return "note/update";
        }

/*        if(note.getId().isBlank()){
            model.addAttribute("errorMsgId", "Id is mandatory.");
            return "note/update";
        }*/

        if(note.getName().isBlank()){
            model.addAttribute("errorMsgName", "Name is mandatory.");
            return "note/update";
        }

        if(null == note.getNote()){
            model.addAttribute("errorMsgNote", "Note is mandatory.");
            return "note/update";
        }

 /*       if(note.getIdPatient() <= 0) {
            model.addAttribute("errorMsgIdPatient", "IdPatient is mandatory.");
            return "note/update";
        }*/

        NoteBean note1 = notesProxy.modifierUneNote(note);
        if(Objects.isNull(note1)) {
            return "note/update";
        }

        List<NoteBean> notes =  notesProxy.listerLesNotesParPatient(idPatient);
        model.addAttribute("notes", notes);
        return "redirect:/note/listby";
    }
    @GetMapping("/note/delete")
    public String effacerUneNote(@RequestParam("id") String id, Model model, @RequestParam("idPatient") int idPatient) {
        logger.info("Je suis dans effacerUneNote de medireportui");

        PatientBean p = patientsProxy.recupererUnPatient(idPatient);
        model.addAttribute("patient", p);

        notesProxy.effacerUneNote(id);

        List<NoteBean> notes =  notesProxy.listerLesNotesParPatient(idPatient);
        model.addAttribute("notes", notes);
        return "redirect:/note/listby";
    }

}
