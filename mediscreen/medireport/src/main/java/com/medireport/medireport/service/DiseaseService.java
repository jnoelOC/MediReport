package com.medireport.medireport.service;

import com.medireport.medireport.beans.NoteBean;
import com.medireport.medireport.beans.PatientBean;
import com.medireport.medireport.model.Disease;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiseaseService {
    private static Logger logger = LoggerFactory.getLogger(DiseaseService.class);

    Disease disease = new Disease();

    private Integer calculateAge(LocalDate dateOfBirth){
        return LocalDate.now().minusYears(dateOfBirth.getYear()).getYear();
    }

    private Set<String> findTrigger(String[] triggers, String noteContent) {
        Set<String> foundTriggers = new HashSet<>();
        for (String trigger : triggers) {
            if (noteContent.contains(trigger)) {
                foundTriggers.add(trigger);
            }
        }
        return foundTriggers;
    }

    public Disease.RiskLevel getRiskLevel(List<NoteBean> notes, PatientBean patient) {
        Disease.RiskLevel result = null;
        Set<String> foundAllTriggers = new HashSet<>();
        int nbOfTrigger = 0;
        int age = 0;
        String[] wantedTriggers = disease.getTriggers();

        for(NoteBean oneNote : notes){
            foundAllTriggers.addAll(findTrigger(wantedTriggers, oneNote.getNote()));
        }

        nbOfTrigger = foundAllTriggers.size();
        age = calculateAge(patient.getDob());

        if(nbOfTrigger == 0 || nbOfTrigger == 1) { result = Disease.RiskLevel.NONE; }
        else if(nbOfTrigger == 2 && (age > 30)) {
            result = Disease.RiskLevel.BORDERLINE;
        }else if(nbOfTrigger == 3 && (age <= 30) && Objects.equals(patient.getSex(), "M")) {
            result = Disease.RiskLevel.IN_DANGER;
        }else if(nbOfTrigger == 4 && (age <= 30) && Objects.equals(patient.getSex(), "F")) {
            result = Disease.RiskLevel.IN_DANGER;
        }else if(nbOfTrigger == 6 && (age > 30)) {
            result = Disease.RiskLevel.IN_DANGER;
        }else if(nbOfTrigger == 5 && (age <= 30) && Objects.equals(patient.getSex(), "M")) {
            result = Disease.RiskLevel.EARLY_ONSET;
        }else if(nbOfTrigger == 7 && (age <= 30) && Objects.equals(patient.getSex(), "F")) {
            result = Disease.RiskLevel.EARLY_ONSET;
        }else if(nbOfTrigger >= 8 && (age > 30)) {
            result = Disease.RiskLevel.EARLY_ONSET;
        }

        if(!notes.isEmpty() && null==result) {result = Disease.RiskLevel.NONE;}
        disease.setRiskLevel(result);

        return result;
    }

}
