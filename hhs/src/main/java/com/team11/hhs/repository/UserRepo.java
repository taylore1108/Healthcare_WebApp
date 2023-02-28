package com.team11.hhs.repository;

import com.team11.hhs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM user u WHERE u.username LIKE %?1%")
    List<User> getContainingUsername(String word);
}
