package com.team11.hhs.repository;

import com.team11.hhs.model.ScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTypeRepo extends JpaRepository<ScheduleType, Long> {
//    ScheduleType findByName(String name);
}
