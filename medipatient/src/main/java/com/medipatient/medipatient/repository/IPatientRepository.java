package com.medipatient.medipatient.repository;


import com.medipatient.medipatient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findById(Long id);

    List<Patient> findAll();

    Patient findByPrenom(String prenom);

}
