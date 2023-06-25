package com.medireport.medireport.service;

import com.medireport.medireport.beans.NoteBean;
import com.medireport.medireport.beans.PatientBean;
import com.medireport.medireport.model.Disease;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class DiseaseService {
    private static Logger logger = LoggerFactory.getLogger(DiseaseService.class);

    Disease disease = new Disease();

    private Integer calculateAge(LocalDate dateOfBirth){
        return LocalDate.now().minusYears(dateOfBirth.getYear()).getYear();
    }

    private Integer findNbOfTrigger(String[] triggers, String noteContent) {
        int numbreOfTriggers = 0;
        for (String trigger : triggers) {
            if (noteContent.contains(trigger)) {
                numbreOfTriggers += 1;
            }
        }
        return numbreOfTriggers;
    }

    public Disease.RiskLevel getRiskLevel(List<NoteBean> notes, PatientBean patient) {
        Disease.RiskLevel result = null;
        int nbOfTrigger = 0;
        String[] wantedTriggers = disease.getTriggers();

        for(NoteBean oneNote : notes){
            nbOfTrigger += findNbOfTrigger(wantedTriggers, oneNote.getNote());
        }

        if(nbOfTrigger == 0) { result = Disease.RiskLevel.NONE; }
        else if(nbOfTrigger == 2 && (calculateAge(patient.getDob()) > 30)) {
            result = Disease.RiskLevel.BORDERLINE;
        }else if(nbOfTrigger == 3 && (calculateAge(patient.getDob()) <= 30)
                && Objects.equals(patient.getSex(), "M")) {
            result = Disease.RiskLevel.IN_DANGER;
        }else if(nbOfTrigger == 4 && (calculateAge(patient.getDob()) <= 30)
                && Objects.equals(patient.getSex(), "F")) {
            result = Disease.RiskLevel.IN_DANGER;
        }else if(nbOfTrigger == 6 && (calculateAge(patient.getDob()) > 30)) {
            result = Disease.RiskLevel.IN_DANGER;
        }else if(nbOfTrigger == 5 && (calculateAge(patient.getDob()) <= 30)
                && Objects.equals(patient.getSex(), "M")) {
            result = Disease.RiskLevel.EARLY_ONSET;
        }else if(nbOfTrigger == 7 && (calculateAge(patient.getDob()) <= 30)
                && Objects.equals(patient.getSex(), "F")) {
            result = Disease.RiskLevel.EARLY_ONSET;
        }else if(nbOfTrigger >= 8 && (calculateAge(patient.getDob()) > 30)) {
            result = Disease.RiskLevel.EARLY_ONSET;
        }

        disease.setRiskLevel(result);

        return result;
    }

}
