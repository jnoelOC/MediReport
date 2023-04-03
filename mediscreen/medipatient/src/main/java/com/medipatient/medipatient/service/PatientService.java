package com.medipatient.medipatient.service;

import com.medipatient.medipatient.model.Patient;
import com.medipatient.medipatient.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private IPatientRepository patientRepository;

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

    public Patient findByPrenom(String prenom)
    {
        return patientRepository.findByPrenom(prenom);
    }

    public Patient save(Patient patient)
    {
        return patientRepository.save(patient);
    }
}
