package com.medipatient.medipatient.ut.utils;

import com.medipatient.medipatient.dto.PatientInfoDTO;
import com.medipatient.medipatient.model.Patient;
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

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PatientMapperTest {

    @InjectMocks
    private PatientMapper patientMapper;

    @BeforeEach
    public void setup() {
    }


    @Test
    @DisplayName("ToPatient")
    void whenValidInputMapToPatient_thenReturnsPatient() throws Exception {
        // ARRANGE
        PatientInfoDTO p1Dto = new PatientInfoDTO("John", "Wayne", LocalDate.of(1909, 2, 3),
                        "M", "Paris", "0123456789");

        // ACT
        Patient result = patientMapper.toPatient(p1Dto);

        // ASSERT
        Assertions.assertEquals("John", result.getFirstname());
        Assertions.assertEquals("Wayne", result.getLastname());
        Assertions.assertEquals("M", result.getSex());
        Assertions.assertEquals(LocalDate.of(1909, 2, 3), result.getDob());
    }


    @Test
    @DisplayName("ToPatientInfoDTO")
    void whenValidInputMapToPatientInfoDTO_thenReturnsPatientInfoDTO() throws Exception {
        // ARRANGE

        Patient p2 = new Patient(2, "Juliette", "Binoche", LocalDate.of(1969, 12, 11),
                "F", "Lyon", "0123456789");
        // ACT
        PatientInfoDTO result = patientMapper.toPatientDTO(p2);

        // ASSERT
        Assertions.assertEquals("Juliette", result.getFamily());
        Assertions.assertEquals("Binoche", result.getGiven());
        Assertions.assertEquals("F", result.getSex());
        Assertions.assertEquals(LocalDate.of(1969, 12, 11), result.getDob());
    }

}
