package com.medipatient.medipatient.repository;


import com.medipatient.medipatient.model.Patient;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {
    @NotNull Optional<Patient> findById(@NotNull Integer id);

    @NotNull List<Patient> findAll();

    void deleteById(@NotNull Integer id);

    Patient findByFirstname(String firstname);

}
