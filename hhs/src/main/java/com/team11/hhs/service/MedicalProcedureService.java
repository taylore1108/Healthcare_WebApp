package com.team11.hhs.service;

import com.team11.hhs.model.MedicalProcedure;
import com.team11.hhs.repository.MedicalProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalProcedureService {
    @Autowired
    private MedicalProcedureRepo medicalProcedureRepo;
    public List<MedicalProcedure> getAllProcedures(){
        return medicalProcedureRepo.findAll();
    }
}
