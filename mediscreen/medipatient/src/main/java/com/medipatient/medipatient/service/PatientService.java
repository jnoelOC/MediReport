package com.medipatient.medipatient.service;

import com.medipatient.medipatient.dto.PatientInfoDTO;
import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.repository.IPatientRepository;
import com.medipatient.medipatient.utils.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private IPatientRepository patientRepository;

    private PatientMapper patientMapper = new PatientMapper();

    public Optional<Patient> findById(Integer id)
    {
        return patientRepository.findById(id);
    }

    public List<Patient> findAll()
    {
        return patientRepository.findAll();
    }

    public void deleteById(Integer id)
    {
        patientRepository.deleteById(id);
    }

    public Patient findByFirstname(String prenom)
    {
        return patientRepository.findByFirstname(prenom);
    }

    public Patient save(Patient patient)
    {
        return patientRepository.save(patient);
    }

    public PatientInfoDTO addPatient(PatientInfoDTO patientDTO)
    {
        PatientInfoDTO pDTO;
        Patient patient = patientMapper.toPatient(patientDTO);
        Patient p = this.patientRepository.save(patient);

        if (p == null) {
            pDTO = null;
        } else {
            pDTO = patientMapper.toPatientDTO(p);
        }
        return pDTO;
    }
}
