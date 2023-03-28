package com.medipatient.medipatient.repository;


import com.medipatient.medipatient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findById(Integer id);

    List<Patient> findAll();

    void deleteById(Integer id);

    Patient findByPrenom(String prenom);

}
