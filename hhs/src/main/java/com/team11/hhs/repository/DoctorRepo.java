package com.team11.hhs.repository;

import com.team11.hhs.model.Doctor;
import com.team11.hhs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d JOIN FETCH d.user u JOIN u.roles r WHERE r.id = 2")
    List<Doctor> findAllDoctorsWithRoleTwo();
}
