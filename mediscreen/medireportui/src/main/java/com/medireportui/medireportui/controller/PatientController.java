package com.medireportui.medireportui.controller;

import com.medireportui.medireportui.beans.PatientBean;
import com.medireportui.medireportui.proxies.MicroservicePatientsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
//import javax.validation.Valid;

@Controller
public class PatientController {

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final MicroservicePatientsProxy patientsProxy;

    public PatientController(MicroservicePatientsProxy patientsProxy){
        this.patientsProxy = patientsProxy;
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

    @GetMapping("/patient/get")
    public String showRecupererUnPatient() {
        logger.info("Je suis dans showRecupererUnPatient de medireportui");

        return "patient/get";
    }

    @GetMapping("/patient/getOne")
     //public ResponseEntity<PatientBean> recupererUnPatient(@RequestParam("id") int id, Model model) {
    public String recupererUnPatient(@RequestParam("id") int id, Model model) {
        logger.info("Je suis dans recupererUnPatient de medireportui");
        logger.error("avant methode recupererUnPatient du proxy ");
        PatientBean patient = patientsProxy.recupererUnPatient(id);
        logger.error("patient numero : " + patient.getId());
        if (Objects.isNull(patient)) {
            logger.error("Erreur dans recupererUnPatient de medireportui : status Non trouvé.");
            model.addAttribute("errorMsgId", "Id is mandatory. Patient not found.");
            //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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

    @GetMapping("/patient/add")
    public String ajouterUnPatientGet() {
        logger.info("Je suis dans ajouterUnPatientGet de medireportui");
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String ajouterUnPatient(Model model, @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
                                   @RequestParam(required=false) String dob, @RequestParam(required=false) String sex,
                                   @RequestParam("address") String address, @RequestParam("phone") String phone) {
        logger.info("Je suis dans ajouterUnPatientPost de medireportui");

        if(firstname.isBlank()){
            model.addAttribute("errorMsgFirst", "Firstname is mandatory.");
            return "patient/add";
        }

        if(lastname.isBlank()){
            model.addAttribute("errorMsgLast", "Lastname is mandatory.");
            return "patient/add";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDateDob = LocalDate.parse(dob, formatter);

        PatientBean patient1 = patientsProxy.ajouterUnPatient(firstname,lastname, localDateDob, sex, address, phone);

        if(null == patient1) {
            return "patient/add";
        }
        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);

        return "patient/list";

    }

    @GetMapping("/patient/delete")
    public String effacerUnPatientGet() {
        logger.info("Je suis dans effacerUnPatientGet de medireportui");

        return "patient/delete";
    }

    @PostMapping("/patient/deleteOne")
    public String effacerUnPatient(Model model, @RequestParam int id) {
        logger.info("Je suis dans effacerUnPatient de medireportui");

        PatientBean p = patientsProxy.recupererUnPatient(id);
        model.addAttribute("patient", p);

        patientsProxy.effacerUnPatient(id);

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/updateOne")
    public String showUpdateGet() {
        return "patient/updateOne";
    }

    @GetMapping("/patient/updateTwo")
    public String updatePatientGet(@RequestParam int id, Model model) {
        logger.info("Je suis dans updatePatientGet de medireportui");
        PatientBean p = patientsProxy.modifierUnPatientGet(id);
        model.addAttribute("patient", p);

         return "patient/updateTwo";
    }

    @PostMapping("/patient/updateTwo")
    public String updatePatientPut(@RequestParam int id, Model model, @RequestParam String firstname, @RequestParam String lastname,
                                   @RequestParam LocalDate dob, @RequestParam String sex, @RequestParam String address,
                                   @RequestParam String phone){
        logger.info("Je suis dans updatePatientPut de medireportui");
        PatientBean patient1 = patientsProxy.modifierUnPatient(id, firstname, lastname, dob, sex, address, phone);

        if(Objects.isNull(patient1)) {
            return "patient/update";
        }

        List<PatientBean> patients =  patientsProxy.listerLesPatients();
        model.addAttribute("patients", patients);
        return "redirect:/patient/list";
    }

}
