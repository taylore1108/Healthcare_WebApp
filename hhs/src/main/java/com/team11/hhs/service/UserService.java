package com.team11.hhs.service;

import com.team11.hhs.model.User;
import com.team11.hhs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepository;
    public User createUser(User user){
        return userRepository.save(user);
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public void deleteUser(Long ID) {
        userRepository.deleteById(ID);
    }

    public User updateUser(Long ID, User newUser) {
        User user = userRepository.findById(ID).get();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setFirstname(newUser.getFirstname());
        user.setLastname(newUser.getLastname());
        user.setRole(newUser.getRole());
        return userRepository.save(newUser);

    }
}
