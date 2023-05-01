package com.team11.hhs.service;

import com.team11.hhs.model.BillingInfo;

import java.util.List;

public interface BillingInfoService {
    void saveBillingInfo(BillingInfo info);

    BillingInfo findByPatientID(Long id);

    List<BillingInfo> findAllBillingInfo();

    void deleteBillingInfo(Long billID);

    void updateNillingInfo(BillingInfo billingInfo);
}
