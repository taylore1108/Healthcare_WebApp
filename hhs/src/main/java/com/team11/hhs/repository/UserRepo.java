package com.team11.hhs.repository;

import com.team11.hhs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
