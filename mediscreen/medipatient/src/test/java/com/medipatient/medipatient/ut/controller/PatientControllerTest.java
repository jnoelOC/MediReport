package com.medipatient.medipatient.ut.controller;

import java.time.LocalDate;
import java.util.*;

import com.medipatient.medipatient.dto.PatientInfoDTO;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.PatientService;
import com.medipatient.medipatient.web.controller.PatientController;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PatientControllerTest {


    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;


    @BeforeEach
    public void setup() {

    }


    @Test
    @DisplayName("findAllPatients")
    void whenValidInputFindAll_thenReturnsListOfString() throws Exception {
        // ARRANGE
        List<Patient> patients = Arrays.asList(
                new Patient(1, "John", "Wayne", LocalDate.of(1909,2,3),
                        "M", "Paris", "0123456789"),
                new Patient(2, "Juliette", "Binoche", LocalDate.of(1969,12,11),
                        "F", "Lyon", "0123456789"));

        when(patientService.findAll()).thenReturn(patients);

        // ACT
        List<Patient> result = patientController.listerTousLesPatients();

        // ASSERT
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("John", result.get(0).getFirstname());
        Assertions.assertEquals("Wayne", result.get(0).getLastname());
        Assertions.assertEquals("M", result.get(0).getSex());
        Assertions.assertEquals(LocalDate.of(1909, 2, 3), result.get(0).getDob());
        Assertions.assertEquals("Juliette", result.get(1).getFirstname());
        Assertions.assertEquals("Binoche", result.get(1).getLastname());
        Assertions.assertEquals("F", result.get(1).getSex());
        Assertions.assertEquals(LocalDate.of(1969, 12, 11), result.get(1).getDob());
    }

    @Test
    @DisplayName("findAllPatientsAtEmpty")
    void whenNullInputFindAll_thenReturnsEmpty() throws Exception {
        // ARRANGE
        List<Patient> patients =  Collections.<Patient>emptyList();
        when(patientService.findAll()).thenReturn(patients);

        // ACT
        List<Patient> result = patientController.listerTousLesPatients();

        // ASSERT
        assertThat(result).isEmpty();
    }


    @Test
    @DisplayName("AddPatient")
    void whenValidInputAddPatient_thenReturnsAPatient() throws Exception {
        // ARRANGE
        Patient patientToCreate = new Patient(null, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        Patient createdPatient = new Patient(1, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        when(patientService.save(any(Patient.class))).thenReturn(createdPatient);

        // ACT
        ResponseEntity<Patient> result = patientController.ajouterUnPatient(patientToCreate);

        // ASSERT
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(createdPatient, result.getBody());
    }


    @Test
    @DisplayName("AddPatientAtNull")
    void whenNullInputAddPatient_thenReturnsNull() throws Exception {
        // ARRANGE
        Patient patientToCreate = new Patient(null, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        Patient p1 = null;
        when(patientService.save(any(Patient.class))).thenReturn(null);

        // ACT
        ResponseEntity<Patient> result = patientController.ajouterUnPatient(patientToCreate);

        // ASSERT
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Assertions.assertEquals(null, result.getBody());
    }


 /*   @Test
    @DisplayName("AddPatientDTO")
    void whenValidInputAddPatientDTO_thenReturnsAPatientDTO() throws Exception {
        // ARRANGE
        PatientInfoDTO createdPatient = new PatientInfoDTO("Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        Patient patientToCreate = new Patient(1, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        when(patientService.addPatient(any(PatientInfoDTO.class))).thenReturn(createdPatient);

        // ACT
        ResponseEntity<Patient> result = patientController.ajouterUnPatient(patientToCreate);

        // ASSERT
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(createdPatient, result.getBody());
    }


    @Test
    @DisplayName("AddPatientDTOAtNull")
    void whenNullInputAddPatientDTO_thenReturnsNull() throws Exception {
        // ARRANGE
        Patient patientToCreate = new Patient(null, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        when(patientService.addPatient(any(PatientInfoDTO.class))).thenReturn(null);

        // ACT
        ResponseEntity<Patient> result = patientController.ajouterUnPatient(patientToCreate);

        // ASSERT
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Assertions.assertEquals(null, result.getBody());
    }
*/



    @Test
    @DisplayName("UpdatePatientGet")
    void whenValidInputUpdatePatientGet_thenReturnsAPatient() throws Exception {
        // ARRANGE
        Optional<Patient> p1 = Optional.empty();
        Patient p2 = new Patient(1, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        p1 = Optional.of(p2);
        List<Patient> ls = new ArrayList<>();
        ls.add(p2);
        int id = 1;
        when(patientService.findAll()).thenReturn(ls);
        when(patientService.findById(any(Integer.class))).thenReturn(p1);

        // ACT
        //ResponseEntity<Patient> result = patientController.modifierUnPatientGet(id);
        Patient result = patientController.modifierUnPatientGet(id);

        // ASSERT
        //Assertions.assertEquals(HttpStatus.FOUND, result.getStatusCode());
        //Assertions.assertEquals(p1.get(), result.getBody());
        Assertions.assertEquals(p1.get().getId(), result.getId());

    }

    @Test
    @DisplayName("UpdatePatientGetAtNull")
    void whenInvalidInputUpdatePatientGet_thenReturnsNull() throws Exception {
        // ARRANGE
        Optional<Patient> p1 = Optional.empty();
        Patient p2 = new Patient(1, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        p1 = Optional.of(p2);

        int id = 1;
        when(patientService.findById(any(Integer.class))).thenReturn(null);

        // ACT
        //ResponseEntity<Patient> result = patientController.modifierUnPatientGet(id);
        Patient result = patientController.modifierUnPatientGet(id);

        // ASSERT
        //Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        //Assertions.assertEquals(null, result.getBody());
        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("UpdatePatientPost")
    void whenValidInputUpdatePatientPost_thenReturnsAModifiedPatient() throws Exception {
        // Arrange
        int id = 1;
        Patient patientToUpdate = new Patient(id, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "M","123 Fame st. Los Angeles", "0123456789");
        Patient updatedPatient = new Patient(id, "Marylin", "Monroe",LocalDate.of(1995, 5, 5)
                ,"F","123 Fame st. Los Angeles", "0123456789");
        when(patientService.save(patientToUpdate)).thenReturn(updatedPatient);

        // Act
        ResponseEntity<Patient> result = patientController.modifierUnPatient(patientToUpdate);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(updatedPatient, result.getBody());
    }

    @Test
    @DisplayName("UpdatePatientPost")
    void whenInvalidInputUpdatePatientPost_thenReturnsNull() throws Exception {
        // Arrange
        int id = 1;
        Patient patientToUpdate = new Patient(id, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "M","123 Fame st. Los Angeles", "0123456789");
        when(patientService.save(patientToUpdate)).thenReturn(null);

        // Act
        ResponseEntity<Patient> result = patientController.modifierUnPatient(patientToUpdate);

        // Assert
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Assertions.assertEquals(null, result.getBody());
    }

    @Test
    @DisplayName("DeletePatient_Ok")
    void whenValidInputDeletePatient_thenReturnsOk() throws Exception {
        // ARRANGE
        int id = 1;
        List<Patient> lp = new ArrayList<>();
        Patient p1 = new Patient(1, "John", "Wayne", LocalDate.of(1909,2,3),
                "M", "Paris", "0123456789");
        Patient p2 = new Patient(2, "Marylin", "Monroe", LocalDate.of(1930,6,6)
                , "F","Los Angeles", "9876543210");
        lp.add(p1); lp.add(p2);
        when(patientService.findAll()).thenReturn(lp);

        // Act
        ResponseEntity<HttpStatus> result = patientController.effacerUnPatient(id);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    //    Assertions.assertEquals(HttpStatus.OK, result.getBody());

    }

    @Test
    @DisplayName("DeletePatient_NotFound")
    void whenInvalidInputDeletePatient_thenReturnsNotFound() throws Exception {
        // ARRANGE
        int id = 3;
        List<Patient> lp = new ArrayList<>();
        Patient p1 = new Patient(1, "John", "Wayne", LocalDate.of(1909,2,3),
                "M", "Paris", "0123456789");
        Patient p2 = new Patient(2, "Marylin", "Monroe", LocalDate.of(1930,6,6)
                , "F","Los Angeles", "9876543210");
        lp.add(p1); lp.add(p2);
        when(patientService.findAll()).thenReturn(lp);

        // Act
        ResponseEntity<HttpStatus> result = patientController.effacerUnPatient(id);

        // Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    //    Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getBody());

    }

    @Test
    @DisplayName("Get Patient by id FOUND")
    void whenValidInputGetPatientById_thenReturnsFound() throws Exception {
        // ARRANGE
        int id = 1;
        Patient p1 = new Patient(1, "John", "Wayne", LocalDate.of(1909,2,3),
                "M", "Paris", "0123456789");
        when(patientService.findById(any(Integer.class))).thenReturn(Optional.of(p1));

        // Act
        Patient patient = patientController.recupererUnPatient(id);

        // Assert
        Assertions.assertNotNull(patient);
    }

    @Test
    @DisplayName("Get Patient by id NOT FOUND")
    void whenInvalidInputGetPatientById_thenReturnsNotFound() throws Exception {
        // ARRANGE
        int id = 1;
        when(patientService.findById(any(Integer.class))).thenReturn(Optional.empty());

        // Act
        Patient result = patientController.recupererUnPatient(id);

        // Assert
        Assertions.assertNull(result);

    }

    @Test
    @DisplayName("Get Patient by id at zero")
    void whenInvalidInputGetPatientByIdAtZero_thenReturnsNotFound() throws Exception {
        // ARRANGE
        int id = 0;
        when(patientService.findById(any(Integer.class))).thenReturn(Optional.empty());

        // Act
        Patient result = patientController.recupererUnPatient(id);

        // Assert
        Assertions.assertNull(result);

    }

}


