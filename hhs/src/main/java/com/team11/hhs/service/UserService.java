package com.team11.hhs.service;

import com.team11.hhs.model.User;
import com.team11.hhs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        User quote = userRepository.findById(ID).get();
        quote.setUserFirstName(newUser.getUserFirstName());
        return userRepository.save(newUser);
    }
}
