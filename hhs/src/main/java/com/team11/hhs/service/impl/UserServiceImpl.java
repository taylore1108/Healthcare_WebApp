package com.team11.hhs.service.impl;

import com.team11.hhs.DTO.UserDTO;
import com.team11.hhs.model.Role;
import com.team11.hhs.model.User;
import com.team11.hhs.repository.RoleRepo;
import com.team11.hhs.repository.UserRepo;
import com.team11.hhs.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepository,
                           RoleRepo roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_PATIENT"); //TODO: This needs to be editable in html
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    public ResponseEntity<User> readByID(@PathVariable("userID") Long id) {
        return ResponseEntity.of(userRepository.findById(id));
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDTO mapToUserDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
    public void deleteUser(@PathVariable Long ID) {
        userRepository.deleteById(ID);
    }

    public void updatePassword( @PathVariable Long ID, String newPassword) {
        User user = userRepository.findById(ID).get();
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public User updateUser(Long ID, User newUser) {
        User user = userRepository.findById(ID).get();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setFirstname(newUser.getFirstname());
        user.setLastname(newUser.getLastname());
        return userRepository.save(newUser);

    }

}
