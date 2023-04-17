package com.team11.hhs.repository;

import com.team11.hhs.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    Patient findByid(Long id);
}
