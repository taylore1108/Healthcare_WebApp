package com.team11.hhs.service;

import com.team11.hhs.model.Bed;
import com.team11.hhs.DTO.BedDTO;

import java.util.List;

public interface BedService {

    void saveBed(Bed bed);

    Bed findByBedName(String bedName);

    List<Bed> findAllBeds();

    void deleteBed(String name);

    void updateBed(BedDTO bed);

}
