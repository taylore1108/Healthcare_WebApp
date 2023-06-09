package com.team11.hhs.service;

import com.team11.hhs.DTO.UserDTO;
import com.team11.hhs.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
    void saveUser(UserDTO userDto);

    User findByUsername(String username);

    List<UserDTO> findAllUsers();


    ResponseEntity<User> findbyId(Long patientID);
}
