package com.team11.hhs.service;

import com.team11.hhs.model.Bills;

import java.util.List;

public interface BillService {
    void saveBill(Bills bill);

    Bills findByPatientID(Long id);
    Bills findByScheduleTypeID(Long id);

    Bills findByDate(String data);

    List<Bills> findAllBills();
    List<Bills> findAllBillsPerPatient(Long id);
    void deleteBill(String name);

    void updateBillPaid(boolean paid);

    void updateBill(Bills newBill);
}
