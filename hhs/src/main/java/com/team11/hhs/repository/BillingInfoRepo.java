package com.team11.hhs.repository;

import com.team11.hhs.model.BillingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingInfoRepo extends JpaRepository<BillingInfo, Long> {
//    BillingInfo findByPatientID(Long ID);
}
