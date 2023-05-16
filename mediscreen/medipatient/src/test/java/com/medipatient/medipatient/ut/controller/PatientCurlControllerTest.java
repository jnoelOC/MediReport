package com.medipatient.medipatient.ut.controller;

import com.medipatient.medipatient.dto.PatientInfoDTO;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.PatientService;
import com.medipatient.medipatient.web.controller.PatientController;
import com.medipatient.medipatient.web.controller.curl.PatientCurlController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PatientCurlControllerTest {

    @InjectMocks
    private PatientCurlController patientCurlController;

    @Mock
    private PatientService patientService;


    @BeforeEach
    public void setup() {

    }


    @Test
    @DisplayName("findAllCurlPatients")
    void whenValidInputFindAll_thenReturnsListOfString() throws Exception {
        // ARRANGE
        List<Patient> patients = Arrays.asList(
                new Patient(1, "John", "Wayne", LocalDate.of(1909,2,3),
                        "M", "Paris", "0123456789"),
                new Patient(2, "Juliette", "Binoche", LocalDate.of(1969,12,11),
                        "F", "Lyon", "0123456789"));

        when(patientService.findAll()).thenReturn(patients);

        // ACT
        ResponseEntity<List<Patient>> result = patientCurlController.listerTousLesPatients();

        // ASSERT
        Assertions.assertEquals(HttpStatus.FOUND, result.getStatusCode());
        Assertions.assertEquals(patients, result.getBody());
    }

    @Test
    @DisplayName("findAllCurlPatientsAtEmpty")
    void whenInvalidInputFindAll_thenReturnsNotFound() throws Exception {
        // ARRANGE
        when(patientService.findAll()).thenReturn(Collections.emptyList());

        // ACT
        ResponseEntity<List<Patient>> result = patientCurlController.listerTousLesPatients();

        // ASSERT
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        Assertions.assertEquals(Collections.emptyList(), result.getBody());
    }

    @Test
    @DisplayName("AddCurlPatient")
    void whenValidInputAddPatient_thenReturnsAPatient() throws Exception {
        // ARRANGE
        PatientInfoDTO patientToCreate = new PatientInfoDTO("Marylin", "Monroe"
                , LocalDate.of(1932,6,12), "F","Los Angeles", "0123456789");
        PatientInfoDTO createdPatient = new PatientInfoDTO("Marylin", "Monroe"
                , LocalDate.of(1932,6,12), "F","Los Angeles", "0123456789");

        when(patientService.addPatient(any(PatientInfoDTO.class))).thenReturn(createdPatient);

        // ACT
        ResponseEntity<PatientInfoDTO> result = patientCurlController.ajouterUnPatient(patientToCreate);

        // ASSERT
        Assertions.assertEquals(HttpStatus.FOUND, result.getStatusCode());
        Assertions.assertEquals(createdPatient, result.getBody());
    }


    @Test
    @DisplayName("AddPatientAtNull")
    void whenNullInputAddPatient_thenReturnsNull() throws Exception {
        // ARRANGE
        PatientInfoDTO patientToCreate = new PatientInfoDTO("Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");

        when(patientService.addPatient(any(PatientInfoDTO.class))).thenReturn(null);

        // ACT
        ResponseEntity<PatientInfoDTO> result = patientCurlController.ajouterUnPatient(patientToCreate);

        // ASSERT
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        Assertions.assertEquals(null, result.getBody());
    }

}
