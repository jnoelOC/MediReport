package com.medireport.medireport.ut.controller;

import com.medireport.medireport.beans.NoteBean;
import com.medireport.medireport.beans.PatientBean;
import com.medireport.medireport.model.Disease;
import com.medireport.medireport.proxies.MicroserviceNotesProxy;
import com.medireport.medireport.proxies.MicroservicePatientsProxy;
import com.medireport.medireport.service.DiseaseService;
import com.medireport.medireport.web.controller.DiseaseController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DiseaseControllerTest {

    @InjectMocks
    private DiseaseController diseaseController;

    @Mock
    private DiseaseService diseaseService;

    @Mock
    private MicroserviceNotesProxy notesProxy;

    @Mock
    private MicroservicePatientsProxy patientsProxy;

    @BeforeEach
    public void setup() {   }


    @Test
    @DisplayName("getRiskLevelForPatient")
    void whenValidInputGetRiskLevelForPatient_thenReturnsRiskLevel() throws Exception {
        // ARRANGE
        Disease.RiskLevel riskLevel = Disease.RiskLevel.BORDERLINE;
        Model model = mock(Model.class);
        int idPatient = 1;

        List<NoteBean> notes = new ArrayList<>();
        NoteBean note1 = new NoteBean();
        note1.setId("1");        note1.setName("note1");
        note1.setIdPatient(1);   note1.setNote("smoker microalbumin");
        NoteBean note2 = new NoteBean();
        note2.setId("2");        note2.setName("note1");
        note2.setIdPatient(1);   note2.setNote("blabla microalbumin");
        notes.add(note1); notes.add(note2);

        PatientBean patient = new PatientBean();
        patient.setId(1);        patient.setFirstname("John"); patient.setLastname("Wayne");
        patient.setDob(LocalDate.of(1909,2,3));
        patient.setSex("M");     patient.setAddress("Paris"); patient.setPhone("0123456789");

        when(patientsProxy.recupererUnPatient(anyInt())).thenReturn(patient);
        when(notesProxy.listerLesNotesParPatient(anyInt())).thenReturn(notes);

        model.addAttribute("errorMsg", riskLevel.name());
        when(model.addAttribute("errorMsg", riskLevel.name())).thenReturn(model);
        when(diseaseService.getRiskLevel(anyList(), any(PatientBean.class))).thenReturn(riskLevel);

        // ACT
//        model.addAttribute("patient", patient);

        //Object result = model.get("errorMsg");
        Disease.RiskLevel result = diseaseController.reportRiskLevelOfNotes(idPatient, model);

        // ASSERT
        Assertions.assertEquals(Disease.RiskLevel.BORDERLINE, result);
    }


}
