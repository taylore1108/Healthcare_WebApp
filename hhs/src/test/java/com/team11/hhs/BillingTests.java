package com.team11.hhs;

import com.team11.hhs.model.Bills;
import com.team11.hhs.service.BillService;
import com.team11.hhs.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
public class BillingTests {
    //Test creating new Bill
    @Test
    public void testCreateBill() {
        BillService billService = Mockito.mock(BillService.class);
        Bills bill = new Bills();
        bill.setPatientUsername("Test");
        bill.setBillPrice(0);
        bill.setProcedureName("Test");
        //userService.createBill(bill);
        Mockito.verify(billService).createBill(bill.getPatientUsername(), bill.getProcedureName());
    }

    //Test retrieving patient bills
    @Test
    public void testGetPatientBills(){
        BillService billService = Mockito.mock(BillService.class);
        Bills bill = new Bills(false, "Test", "Test", 0);
        billService.createBill(bill.getPatientUsername(), bill.getProcedureName());
        Mockito.verify(billService).getBillsForUser(bill.getPatientUsername());

    }
}
