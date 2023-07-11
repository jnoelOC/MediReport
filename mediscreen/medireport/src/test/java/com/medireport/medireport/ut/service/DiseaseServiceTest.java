package com.medireport.medireport.ut.service;

import com.medireport.medireport.beans.NoteBean;
import com.medireport.medireport.beans.PatientBean;
import com.medireport.medireport.model.Disease;
import com.medireport.medireport.service.DiseaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DiseaseServiceTest {

    @InjectMocks
    private DiseaseService diseaseService;

//    @Mock
//    private IPatientRepository patientRepository;


    @BeforeEach
    public void setup() { }

    @Test
    @DisplayName("getRiskLevel with 10 triggers and age < 30 at None")
    void whenValidInputGetRiskLevel_thenReturnsNone() throws Exception {

        // ARRANGE
        Disease.RiskLevel riskLevel = null;
        List<NoteBean> notes = new ArrayList<>();
        NoteBean note1 = new NoteBean();
        note1.setId("1");        note1.setName("note1");
        note1.setIdPatient(1);   note1.setNote("cholesterol smoker reaction relapse dizziness abnormal");
        NoteBean note2 = new NoteBean();
        note2.setId("2");        note2.setName("note1");
        note2.setIdPatient(1);   note2.setNote("blabla weight height antibodies microalbumin");
        notes.add(note1); notes.add(note2);

        PatientBean patient = new PatientBean();
        patient.setId(1);        patient.setFirstname("John"); patient.setLastname("Wayne");
        patient.setDob(LocalDate.of(2009,2,3));
        patient.setSex("M");     patient.setAddress("Paris"); patient.setPhone("0123456789");

        // ACT
        riskLevel = diseaseService.getRiskLevel(notes, patient);

        // ASSERT
        Assertions.assertEquals(Disease.RiskLevel.NONE, riskLevel);
    }

    @Test
    @DisplayName("getRiskLevel with 10 triggers and age > 30 at EarlyOnSet")
    void whenValidInputGetRiskLevel_thenReturnsEarlyOnSet() throws Exception {

        // ARRANGE
        Disease.RiskLevel riskLevel = null;
        List<NoteBean> notes = new ArrayList<>();
        NoteBean note1 = new NoteBean();
        note1.setId("1");        note1.setName("note1");
        note1.setIdPatient(1);   note1.setNote("cholesterol smoker reaction relapse dizziness abnormal");
        NoteBean note2 = new NoteBean();
        note2.setId("2");        note2.setName("note1");
        note2.setIdPatient(1);   note2.setNote("blabla weight height antibodies microalbumin");
        notes.add(note1); notes.add(note2);

        PatientBean patient = new PatientBean();
        patient.setId(1);        patient.setFirstname("John"); patient.setLastname("Wayne");
        patient.setDob(LocalDate.of(1903,2,3));
        patient.setSex("M");     patient.setAddress("Paris"); patient.setPhone("0123456789");

        // ACT
        riskLevel = diseaseService.getRiskLevel(notes, patient);

        // ASSERT
        Assertions.assertEquals(Disease.RiskLevel.EARLY_ONSET, riskLevel);
    }


    @Test
    @DisplayName("getRiskLevel at None with one trigger")
    void whenValidInputGetRiskLevelWithOneTrigger_thenReturnsNone() throws Exception {

        // ARRANGE
        Disease.RiskLevel riskLevel = Disease.RiskLevel.NONE;
        List<NoteBean> notes = new ArrayList<>();
        NoteBean note1 = new NoteBean();
        note1.setId("1");        note1.setName("note1");
        note1.setIdPatient(1);   note1.setNote("microalbumin");
        NoteBean note2 = new NoteBean();
        note2.setId("2");        note2.setName("note1");
        note2.setIdPatient(1);   note2.setNote("blabla microalbumin");
        notes.add(note1); notes.add(note2);

        PatientBean patient = new PatientBean();
        patient.setId(1);        patient.setFirstname("John"); patient.setLastname("Wayne");
        patient.setDob(LocalDate.of(1909,2,3));
        patient.setSex("M");     patient.setAddress("Paris"); patient.setPhone("0123456789");

        // ACT
        riskLevel = diseaseService.getRiskLevel(notes, patient);

        // ASSERT
        Assertions.assertEquals(Disease.RiskLevel.NONE, riskLevel);
    }

    @Test
    @DisplayName("getRiskLevel at Borderline with two triggers")
    void whenValidInputGetRiskLevelWithTwoTriggers_thenReturnsBorderline() throws Exception {

        // ARRANGE
        Disease.RiskLevel riskLevel = Disease.RiskLevel.NONE;
        List<NoteBean> notes = new ArrayList<>();
        NoteBean note1 = new NoteBean();
        note1.setId("1");        note1.setName("note1");
        note1.setIdPatient(1);   note1.setNote("microalbumin");
        NoteBean note2 = new NoteBean();
        note2.setId("2");        note2.setName("note1");
        note2.setIdPatient(1);   note2.setNote("smoker microalbumin");
        notes.add(note1); notes.add(note2);

        PatientBean patient = new PatientBean();
        patient.setId(1);        patient.setFirstname("John"); patient.setLastname("Wayne");
        patient.setDob(LocalDate.of(1909,2,3));
        patient.setSex("M");     patient.setAddress("Paris"); patient.setPhone("0123456789");

        // ACT
        riskLevel = diseaseService.getRiskLevel(notes, patient);

        // ASSERT
        Assertions.assertEquals(Disease.RiskLevel.BORDERLINE, riskLevel);
    }

    @Test
    @DisplayName("getRiskLevel at InDanger with three triggers")
    void whenValidInputGetRiskLevelWithThreeTriggers_thenReturnsBorderline() throws Exception {

        // ARRANGE
        Disease.RiskLevel riskLevel = null;
        List<NoteBean> notes = new ArrayList<>();
        NoteBean note1 = new NoteBean();
        note1.setId("1");        note1.setName("note1");
        note1.setIdPatient(1);   note1.setNote("smoker microalbumin");
        NoteBean note2 = new NoteBean();
        note2.setId("2");        note2.setName("note1");
        note2.setIdPatient(1);   note2.setNote("blabla weight");
        notes.add(note1); notes.add(note2);

        PatientBean patient = new PatientBean();
        patient.setId(1);        patient.setFirstname("John"); patient.setLastname("Wayne");
        patient.setDob(LocalDate.of(1903,2,3));
        patient.setSex("M");     patient.setAddress("Paris"); patient.setPhone("0123456789");

        // ACT
        riskLevel = diseaseService.getRiskLevel(notes, patient);

        // ASSERT
        Assertions.assertEquals(Disease.RiskLevel.IN_DANGER, riskLevel);
    }

}
