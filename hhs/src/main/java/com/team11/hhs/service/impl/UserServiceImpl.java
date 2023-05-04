package com.team11.hhs.service.impl;

import com.team11.hhs.DTO.UserDTO;
import com.team11.hhs.model.Bed;
import com.team11.hhs.DTO.BedDTO;
import com.team11.hhs.model.Role;
import com.team11.hhs.model.User;
import com.team11.hhs.repository.BedRepo;
import com.team11.hhs.repository.RoleRepo;
import com.team11.hhs.repository.UserRepo;
import com.team11.hhs.service.BedService;
import com.team11.hhs.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, BedService {
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final BedRepo bedRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepository,
                           RoleRepo roleRepository,
                           BedRepo bedRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.bedRepository = bedRepository;
    }

    @Override
    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        String roleName = userDto.getRole();
        Role role = roleRepository.findByName(roleName);
        if(role == null){
            role = checkRoleExist(roleName);
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
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

    @Override
    public ResponseEntity<User> findbyId(Long patientID) {
        return  ResponseEntity.of(userRepository.findById(patientID));
    }

    public ResponseEntity<User> readByID(@PathVariable("userID") Long id) {
        return ResponseEntity.of(userRepository.findById(id));
    }
    public UserDTO mapToUserDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private Role checkRoleExist(String roleName){
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    private Bed checkBedExist(String bedName){
        Bed bed = new Bed();
        bed.setName(bedName);
        return bedRepository.save(bed);
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

    @Override
    public void saveBed(Bed bed) {
        bedRepository.save(bed);
    }

    @Override
    public Bed findByBedName(String bedName) {
        return bedRepository.findByName(bedName);
    }

    @Override
    public List<Bed> findAllBeds() {
        return bedRepository.findAll();
    }

    @Override
    public void deleteBed(String name) {
        Long id = findByBedName(name).getId();
        bedRepository.deleteById(id);
    }

    @Override
    public void updateBed(BedDTO bed) {

        User user= userRepository.findByUsername(bed.getUsername());
        Bed newbed = bedRepository.findByName(bed.getName());
        if(user != null){
            newbed.setPatientID(user.getId());
        }
        else{
            newbed.setPatientID(null);
        }

        bedRepository.save(newbed);
    }

}
