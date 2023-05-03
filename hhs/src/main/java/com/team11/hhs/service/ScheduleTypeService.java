package com.team11.hhs.service;

import com.team11.hhs.model.ScheduleType;

import java.util.List;

public interface ScheduleTypeService {
    void saveScheduleType(ScheduleType scheduleType);

    ScheduleType findByName(String Name);

    List<ScheduleType> findAllScheduleTypes();

    void deleteScheduleType(String name);

    void updateScheduleType(ScheduleType newType);
}
