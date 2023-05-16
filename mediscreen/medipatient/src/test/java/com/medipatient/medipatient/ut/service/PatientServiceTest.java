package com.medipatient.medipatient.ut.service;

import com.medipatient.medipatient.dto.PatientInfoDTO;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.repository.IPatientRepository;
import com.medipatient.medipatient.service.PatientService;
import com.medipatient.medipatient.utils.PatientMapper;
import com.medipatient.medipatient.web.controller.PatientController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private IPatientRepository patientRepository;


    @BeforeEach
    public void setup() { }

    @Test
    @DisplayName("FindById")
    void whenValidInputFindById_thenReturnsListOfString() throws Exception {

        // ARRANGE
        Optional<Patient> p1 = Optional.of(new Patient(1, "John", "Wayne"
                , LocalDate.of(1909, 2, 3),"M", "Paris", "0123456789"));

        when(patientRepository.findById(any(Integer.class))).thenReturn(p1);

        // ACT
        Optional<Patient> result = patientService.findById(1);

        // ASSERT
        Assertions.assertEquals(p1.get(), result.get());
    }

    @Test
    @DisplayName("FindAll")
    void whenValidInputFindAll_thenReturnsListOfString() throws Exception {

        // ARRANGE
        List<Patient> patients = Arrays.asList(
                new Patient(1, "John", "Wayne", LocalDate.of(1909,2,3),
                        "M", "Paris", "0123456789"),
                new Patient(2, "Juliette", "Binoche", LocalDate.of(1969,12,11),
                        "F", "Lyon", "0123456789"));

        when(patientRepository.findAll()).thenReturn(patients);

        // ACT
        List<Patient> result = patientService.findAll();

        // ASSERT
        Assertions.assertEquals(patients.size(), result.size());
    }

    @Test
    @DisplayName("FindByFirstname")
    void whenValidInputFindByFirstname_thenReturnsPatient() throws Exception {

        // ARRANGE
        String firstname = "Marylin";
        Patient p1 = new Patient(1, "John", "Wayne"
                , LocalDate.of(1909, 2, 3),"M", "Paris", "0123456789");

        when(patientRepository.findByFirstname(any(String.class))).thenReturn(p1);

        // ACT
        Patient result = patientService.findByFirstname(firstname);

        // ASSERT
        Assertions.assertEquals(p1.getFirstname(), result.getFirstname());
    }

    @Test
    @DisplayName("Save")
    void whenValidInputSave_thenReturnsPatient() throws Exception {

        // ARRANGE
        Patient p1 = new Patient(1, "John", "Wayne"
                , LocalDate.of(1909, 2, 3),"M", "Paris", "0123456789");
        Patient p2 = new Patient(2, "Juliette", "Binoche"
                , LocalDate.of(1969,12,11),"F", "Lyon", "0123456789");

        when(patientRepository.save(any(Patient.class))).thenReturn(p1);

        // ACT
        Patient result = patientService.save(p2);

        // ASSERT
        Assertions.assertEquals(p1.getFirstname(), result.getFirstname());
    }


    @Test
    @DisplayName("AddPatientDTO")
    void whenValidInputAddPatientDTO_thenReturnsPatientInfoDTO() throws Exception {

        // ARRANGE
        Patient p1 = new Patient(1, "John", "Wayne"
                , LocalDate.of(1909, 2, 3),"M", "Paris", "0123456789");
        PatientInfoDTO p3Dto = new PatientInfoDTO("John", "Kennedy"
                , LocalDate.of(1918,5,10),"F", "Lyon", "0123456789");

        when(patientRepository.save(any(Patient.class))).thenReturn(p1);

        // ACT
        PatientInfoDTO result = patientService.addPatient(p3Dto);

        // ASSERT
        Assertions.assertEquals(p1.getFirstname(), result.getFamily());
    }
}
