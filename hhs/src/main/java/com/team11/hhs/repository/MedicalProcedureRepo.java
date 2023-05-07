package com.team11.hhs.repository;

import com.team11.hhs.model.MedicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalProcedureRepo extends JpaRepository<MedicalProcedure, Integer> {
    MedicalProcedure findByProcedureName(String name);
    MedicalProcedure findById(int id);
}
