package com.medipatient.medipatient.tu.controller;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.service.PatientService;
import com.medipatient.medipatient.web.controller.PatientController;
import jdk.net.SocketFlow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PatientControllerTest {


    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    @Test
    @DisplayName("findAllPatients")
    void whenValidInputFindAll_thenReturnsListOfString() throws Exception {
        // ARRANGE
        List<Patient> patients = Arrays.asList(
                new Patient(1, "John", "Wayne", LocalDate.of(1909,2,3),
                        "M", "Paris", "0123456789"),
                new Patient(2, "Juliette", "Binoche", LocalDate.of(1969,12,11),
                        "F", "Lyon", "0123456789")
        );

        when(patientService.findAll()).thenReturn(patients);

        // ACT and ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/patients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname").value("Wayne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dob[0]").value("1909"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dob[1]").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dob[2]").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sex").value("M"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("Paris"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phone").value("0123456789"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstname").value("Juliette"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastname").value("Binoche"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dob[0]").value("1969"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dob[1]").value("12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dob[2]").value("11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sex").value("F"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address").value("Lyon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phone").value("0123456789"));
    }

    @Test
    @DisplayName("findAllPatientsAtEmpty")
    void whenNullInputFindAll_thenReturnsEmpty() throws Exception {
        // ARRANGE
        List<Patient> patients =  Collections.<Patient>emptyList();
        when(patientService.findAll()).thenReturn(patients);

        // ACT and ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/patients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @DisplayName("AddPatient")
    void whenValidInputAddPatient_thenReturnsAPatient() throws Exception {
        // ARRANGE
        Patient p1 = new Patient(1, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        when(patientService.save(any(Patient.class))).thenReturn(p1);

        // ACT and ASSERT
        mockMvc.perform(MockMvcRequestBuilders.post("/patient/add")
                        .content(objectMapper.writeValueAsString(p1)).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Marylin"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("Monroe"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.dob[0]").value("1932"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.dob[1]").value("6"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.dob[2]").value("12"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.sex").value("F"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Fame st. Los Angeles"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("0123456789"));
    }


    @Test
    @DisplayName("AddPatientAtNull")
    void whenNullInputAddPatient_thenReturnsNull() throws Exception {
        // ARRANGE
        Patient p1 = new Patient(1, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        when(patientService.save(any(Patient.class))).thenReturn(null);

        // ACT and ASSERT
        mockMvc.perform(MockMvcRequestBuilders.post("/patient/add")
                        .content(objectMapper.writeValueAsString(p1)).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @DisplayName("UpdatePatientGet")
    void whenValidInputUpdatePatientGet_thenReturnsAPatient() throws Exception {
        // ARRANGE
        Optional<Patient> p1 = Optional.empty();
        Patient p2 = new Patient(1, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        p1 = Optional.of(p2);
        when(patientService.findById(any(Integer.class))).thenReturn(p1);

        // ACT and ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/patient/update")
                        .param("id","1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Marylin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("Monroe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dob[0]").value("1932"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dob[1]").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dob[2]").value("12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sex").value("F"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Fame st. Los Angeles"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("0123456789"));
    }

    @Test
    @DisplayName("UpdatePatientPost")
    void whenValidInputUpdatePatientPost_thenReturnsAModifiedPatient() throws Exception {
        // ARRANGE
        Patient p1 = new Patient(1, "Marylin", "Monroe", LocalDate.of(1932,6,12)
                , "F","123 Fame st. Los Angeles", "0123456789");
        when(patientService.save(any(Patient.class))).thenReturn(p1);

        // ACT and ASSERT
        mockMvc.perform(MockMvcRequestBuilders.put("/patient/update")
                        .content(objectMapper.writeValueAsString(p1)).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("Marylin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("Monroe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dob[0]").value("1932"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dob[1]").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dob[2]").value("12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sex").value("F"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("123 Fame st. Los Angeles"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("0123456789"));
    }

    @Test
    @DisplayName("DeletePatient_Ok")
    void whenValidInputDeletePatient_thenReturnsOk() throws Exception {
        // ARRANGE
        List<Patient> lp = new ArrayList<>();
        Patient p1 = new Patient(1, "John", "Wayne", LocalDate.of(1909,2,3),
                "M", "Paris", "0123456789");
        Patient p2 = new Patient(2, "Marylin", "Monroe", LocalDate.of(1930,6,6)
                , "F","Los Angeles", "9876543210");
        lp.add(p1); lp.add(p2);
        when(patientService.findAll()).thenReturn(lp);
        // when(patientService.deleteById(any(Integer.class))).thenReturn(p2);

        // ACT and ASSERT
        mockMvc.perform(MockMvcRequestBuilders.post("/patient/delete")
                        .param("id","2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("DeletePatient_NotFound")
    void whenInvalidInputDeletePatient_thenReturnsNotFound() throws Exception {
        // ARRANGE
        List<Patient> lp = new ArrayList<>();
        Patient p1 = new Patient(1, "John", "Wayne", LocalDate.of(1909,2,3),
                "M", "Paris", "0123456789");
        Patient p2 = new Patient(2, "Marylin", "Monroe", LocalDate.of(1930,6,6)
                , "F","Los Angeles", "9876543210");
        lp.add(p1); lp.add(p2);
        when(patientService.findAll()).thenReturn(lp);
       // when(patientService.deleteById(any(Integer.class))).thenReturn(p2);

        // ACT and ASSERT
        mockMvc.perform(MockMvcRequestBuilders.post("/patient/delete")
                        .param("id","3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


/*    @Test
    @DisplayName("AddPatient")
    void whenValidInputAddPatientDTO_thenReturnsListOfString() throws Exception {
        // ARRANGE
        PatientInfoDTO pd1 = new PatientInfoDTO("TestNone", "Test", LocalDate.of(1914,1,1)
                , "F","123 rue de Paris Paris 75000", "0123456789");


        when(patientService.addPatient(any(PatientInfoDTO.class))).thenReturn(pd1);

        // ACT
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/patient/curl/add")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        // ASSERT
        assertThat(contentAsString).isNotNull();

    }*/

}


