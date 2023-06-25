package com.medireport.medireport.web.controller;

import com.medireport.medireport.beans.NoteBean;
import com.medireport.medireport.beans.PatientBean;
import com.medireport.medireport.model.Disease;
import com.medireport.medireport.proxies.MicroserviceNotesProxy;
import com.medireport.medireport.proxies.MicroservicePatientsProxy;
import com.medireport.medireport.service.DiseaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiseaseController {
    private static Logger logger = LoggerFactory.getLogger(DiseaseController.class);

    @Autowired
    private DiseaseService diseaseService;

    private final MicroserviceNotesProxy notesProxy;
    private final MicroservicePatientsProxy patientsProxy;

    public DiseaseController(MicroserviceNotesProxy notesProxy, MicroservicePatientsProxy patientsProxy){
        this.notesProxy = notesProxy;
        this.patientsProxy = patientsProxy;
    }

    @GetMapping("/report/getrisklevel")
//    public String listOfNotes(Model model, @RequestParam(name = "notes") List<NoteBean> notes, @RequestParam(name = "patient") PatientBean patient) {
    public Disease.RiskLevel reportRiskLevelOfNotes(Model model, @RequestParam int idPatient) {
        logger.info("Je suis dans listOfNotes de medireport");
        Disease.RiskLevel rl = null;
                PatientBean patient = patientsProxy.recupererUnPatient(idPatient);
        List<NoteBean> notes = notesProxy.listerLesNotesParPatient(idPatient);

        if (null == notes || notes.isEmpty()){
            logger.info("liste des notes null ou vide !");
            model.addAttribute("errorMsg", "This list is empty.");
        }
        else{
            logger.info("notes trouvées.");
            model.addAttribute("patient", patient);
            rl = diseaseService.getRiskLevel(notes, patient);

            if(null != rl) {
//                logger.info("Le niveau de risque est : {} dans medireport.", rl);
                model.addAttribute("errorMsg", rl.name());
            }
            else {
                model.addAttribute("errorMsg", "Risk level is null.");
            }

        }
//        return "/report/list";
        return rl;
    }

}
