package com.medireportui.medireportui.controller;


//import com.mediscreen.medinotes.model.Note;
import com.medireportui.medireportui.beans.NoteBean;
import com.medireportui.medireportui.beans.PatientBean;
import com.medireportui.medireportui.proxies.MicroserviceDiseasesProxy;
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
public class PatientController {

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);

//    @Autowired
//    private ApiController apiNote;

    private final MicroservicePatientsProxy patientsProxy;
    private final MicroserviceNotesProxy notesProxy;

    private final MicroserviceDiseasesProxy diseasesProxy;
    public PatientController(MicroservicePatientsProxy patientsProxy, MicroserviceNotesProxy notesProxy, MicroserviceDiseasesProxy diseasesProxy){
        this.patientsProxy = patientsProxy;
        this.notesProxy = notesProxy;
        this.diseasesProxy = diseasesProxy;
    }



    @GetMapping({"/", "/home"})
    public String getHome() {
        logger.info("Je suis dans home de medireportui");
        return "home";
    }

    @GetMapping("/patient/list")
//    public ResponseEntity<List<PatientBean>> listOfPatients(Model model) {
    public String listOfPatients(Model model) {
        logger.info("Je suis dans listOfPatients de medireportui");
        List<PatientBean> patients =  patientsProxy.listerLesPatients();

        if (null == patients || patients.isEmpty()){
            logger.info("liste des patients null ou vide !");
//            return new ResponseEntity<>(patients, HttpStatus.NOT_FOUND);
            model.addAttribute("errorMsg", "This list is empty.");
        }
        else{
            logger.info("status patients trouvés.");
//            return new ResponseEntity<>(patients, HttpStatus.FOUND);
            model.addAttribute("patients", patients);
        }
        return "patient/list";
    }

    @GetMapping("/patient/listby/{id}")
    public String listOfNotesByPatient(Model model, @PathVariable("id") int idPatient) {
        logger.info("Je suis dans listOfNotesByPatient de medireportui");

        List<NoteBean> notes = notesProxy.listerLesNotesParPatient(idPatient);
        PatientBean patient = patientsProxy.recupererUnPatient(idPatient);

        if (null == notes || notes.isEmpty()){
            logger.info("liste des notes par patient null ou vide !");
            model.addAttribute("errorMsg", "This list is empty.");
        }
        else{
            logger.info("status notes trouvées.");

        }
        model.addAttribute("patient", patient);
        model.addAttribute("notes", notes);
        return "note/listby";
    }

    @GetMapping("/patient/get")
    public String showRecupererUnPatient() {
        logger.info("Je suis dans showRecupererUnPatient de medireportui");

        return "patient/get";
    }

    @GetMapping("/patient/getOne")
     //public ResponseEntity<PatientBean> recupererUnPatient(@RequestParam("id") int id, Model model) {
    public String recupererUnPatient(@RequestParam int id, Model model) {
        logger.info("Je suis dans recupererUnPatient de medireportui");
        logger.info("avant methode recupererUnPatient du proxy ");
        PatientBean patient = patientsProxy.recupererUnPatient(id);
        logger.error("patient numero : " + patient.getId());
        if (Objects.isNull(patient)) {
            logger.error("Erreur dans recupererUnPatient de medireportui : status Non trouvé.");
            model.addAttribute("errorMsgId", "Id is mandatory. Patient not found.");
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return null;
            return "patient/get";
        }
        else {
            logger.info("patient trouvé.");
            model.addAttribute("patient", patient);
            // return new ResponseEntity<>(patient, HttpStatus.FOUND);
            return "patient/getOne";
        }
    }

    @GetMapping(value = "/patient/add")
    public String ajouterUnPatientGet(Model model) {
        logger.info("Je suis dans ajouterUnPatientGet de medireportui");
        model.addAttribute("patient", new PatientBean());
        return "patient/add";
    }


    @PostMapping( value = "/patient/validate")
    public String ajouterUnPatient(@RequestBody @ModelAttribute("patient") @Valid PatientBean patient, Model model) {

        logger.info("Je suis dans ajouterUnPatientPost de medireportui");

        if (null == patient){
            model.addAttribute("errorMsg", "patient object is null.");
            return "patient/add";
        }

        if(patient.getFirstname().isBlank()){
            model.addAttribute("errorMsgFirst", "Firstname is mandatory.");
            return "patient/add";
        }

        if(patient.getLastname().isBlank()){
            model.addAttribute("errorMsgLast", "Lastname is mandatory.");
            return "patient/add";
        }

        if(null == patient.getDob()){
            model.addAttribute("errorMsgDob", "Date of birthday is mandatory.");
            return "patient/add";
        }
        
        if(patient.getSex().isBlank() || (!patient.getSex().equals("F") && !patient.getSex().equals("M"))){
            model.addAttribute("errorMsgSex", "Gender is mandatory : M or F.");
            return "patient/add";
        }

        if(patient.getAddress().isBlank()){
            model.addAttribute("errorMsgAddress", "Address is mandatory.");
            return "patient/add";
        }
        if(patient.getPhone().isBlank()){
            model.addAttribute("errorMsgPhone", "Phone is mandatory.");
            return "patient/add";
        }

        PatientBean patient1 = patientsProxy.ajouterUnPatient(patient);
        if (null == patient1) {
            return "patient/add";
        }

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);
        return "patient/list";
    }


    @GetMapping("/patient/delete/{id}")
    public String effacerUnPatient(@PathVariable("id") int id, Model model) {
        logger.info("Je suis dans effacerUnPatient de medireportui");

     /*   PatientBean p = patientsProxy.recupererUnPatient(id);
        model.addAttribute("patient", p);
*/
        patientsProxy.effacerUnPatient(id);

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/update/{id}")
    public String updatePatientGet(@PathVariable("id") int id, Model model) {
        logger.info("Je suis dans updatePatientGet de medireportui");
        PatientBean p = patientsProxy.modifierUnPatientGet(id);
        model.addAttribute("patient", p);

         return "patient/update";
    }

    @PostMapping("/patient/update")
    public String updatePatientPut(Model model, @RequestBody  @ModelAttribute("patient") @Valid PatientBean patient){
        logger.info("Je suis dans updatePatientPost de medireportui");

        if (null == patient){
            model.addAttribute("errorMsg", "patient object is null.");
            return "patient/update";
        }

        if(patient.getFirstname().isBlank()){
            model.addAttribute("errorMsgFirst", "Firstname is mandatory.");
            return "patient/update";
        }

        if(patient.getLastname().isBlank()){
            model.addAttribute("errorMsgLast", "Lastname is mandatory.");
            return "patient/update";
        }

        if(null == patient.getDob()){
            model.addAttribute("errorMsgDob", "Date of birthday is mandatory.");
            return "patient/update";
        }

        if(patient.getSex().isBlank() || (!patient.getSex().equals("F") && !patient.getSex().equals("M"))){
            model.addAttribute("errorMsgSex", "Gender is mandatory : M or F.");
            return "patient/update";
        }

        if(patient.getAddress().isBlank()){
            model.addAttribute("errorMsgAddress", "Address is mandatory.");
            return "patient/update";
        }
        if(patient.getPhone().isBlank()){
            model.addAttribute("errorMsgPhone", "Phone is mandatory.");
            return "patient/update";
        }

        PatientBean patient1 = patientsProxy.modifierUnPatient(patient);

        if(Objects.isNull(patient1)) {
            return "patient/update";
        }

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);
        return "redirect:/patient/list";
    }

}
