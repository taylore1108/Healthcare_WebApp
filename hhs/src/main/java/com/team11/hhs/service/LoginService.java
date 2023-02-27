package com.team11.hhs.service;

import com.team11.hhs.controller.UserController;
import com.team11.hhs.model.User;
import com.team11.hhs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class LoginService {

    @Autowired
    UserRepo userRepository;

    public Optional<User> getUserID(Long ID) {return userRepository.findById(ID);}
    //public User getUserPassword(User user) {return userRepository.findOne(user);}
    public boolean validateUser(String userID, String password){
        return userID.equalsIgnoreCase(userID) && password.equalsIgnoreCase(password);
    }
}
