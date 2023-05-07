package com.team11.hhs.repository;

import com.team11.hhs.model.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillRepo extends JpaRepository<Bills, Long> {
//    Bills findByPatientID(Long ID);
//    Bills findByScheduleTypeID(Long ID);
//
//    Bills findByDate(String data);
    List<Bills> findByPatientUsername(String username);
}
