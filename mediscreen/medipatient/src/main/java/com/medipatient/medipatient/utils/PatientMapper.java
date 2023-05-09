package com.medipatient.medipatient.utils;

import com.medipatient.medipatient.dto.PatientInfoDTO;
import com.medipatient.medipatient.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toPatient(PatientInfoDTO patientDTO) {

        Patient patient = null;
        try {
            patient = new Patient(null, patientDTO.getFamily(), patientDTO.getGiven(), patientDTO.getDob(),
                    patientDTO.getSex(), patientDTO.getAddress(), patientDTO.getPhone());
        } catch (NullPointerException e) {
            // logger.error("Error null pointer : " + e);
        } catch (Exception ex) {
            // logger.error("Error general purpose : ", ex);
        }

        return patient;
    }

    public PatientInfoDTO toPatientDTO(Patient patient) {
        PatientInfoDTO patientDTO = null;
        try {
            patientDTO = new PatientInfoDTO(patient.getFirstname(), patient.getLastname(), patient.getDob(),
                    patient.getSex(), patient.getAddress(), patient.getPhone());
        } catch (NullPointerException e) {
            // logger.error("Error null pointer : ", e);
        } catch (Exception ex) {
            // logger.error("Error general purpose : ", ex);
        }

        return patientDTO;
    }


}
