package com.team11.hhs.service;

import com.team11.hhs.model.Bills;
import com.team11.hhs.model.MedicalProcedure;
import com.team11.hhs.repository.BillRepo;
import com.team11.hhs.repository.MedicalProcedureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BillService {

    @Autowired
    private BillRepo billRepo;

    @Autowired
    private MedicalProcedureRepo medicalProcedureRepo;

    public void createBill(String username, String procedureName){
        MedicalProcedure medicalProcedure = medicalProcedureRepo.findByProcedureName(procedureName);
        int price = medicalProcedure.getProcedureCost();
        Bills bill = new Bills(false, username,procedureName, price);
        billRepo.save(bill);
    }

}
