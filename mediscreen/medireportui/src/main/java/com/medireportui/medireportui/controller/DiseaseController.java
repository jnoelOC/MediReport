package com.medireportui.medireportui.controller;

import com.medireportui.medireportui.beans.DiseaseBean;
import com.medireportui.medireportui.beans.NoteBean;
import com.medireportui.medireportui.beans.PatientBean;
import com.medireportui.medireportui.proxies.MicroserviceDiseasesProxy;
import com.medireportui.medireportui.proxies.MicroserviceNotesProxy;
import com.medireportui.medireportui.proxies.MicroservicePatientsProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DiseaseController {

    private static Logger logger = LoggerFactory.getLogger(DiseaseController.class);
    private final MicroserviceDiseasesProxy diseasesProxy;

    private final MicroserviceNotesProxy notesProxy;
    private final MicroservicePatientsProxy patientsProxy;

    public DiseaseController(MicroserviceDiseasesProxy diseasesProxy, MicroserviceNotesProxy notesProxy, MicroservicePatientsProxy patientsProxy){
        this.diseasesProxy = diseasesProxy;
        this.notesProxy = notesProxy;
        this.patientsProxy = patientsProxy;
    }

    @GetMapping(value = "/report/getrisklevelforpatient")
    public String getRiskLevelGet(Model model, @RequestParam int idPatient){

        List<NoteBean> notes = notesProxy.listerLesNotesParPatient(idPatient);
        PatientBean patient = patientsProxy.recupererUnPatient(idPatient);

        DiseaseBean.RiskLevel rl = diseasesProxy.getRiskLevel(idPatient);
//        logger.info("Le niveau de risque est : {} dans medireportui.", rl.name());
        if(null != rl){
            model.addAttribute("errorMsg", rl.name());
        }
        else {
            model.addAttribute("errorMsg", "Risk level is null.");
        }
        model.addAttribute("patient", patient);

        return "report/list";
    }

}