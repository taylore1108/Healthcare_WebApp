package com.team11.hhs.repository;

import com.team11.hhs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
//    User findById(Long Id);
}
