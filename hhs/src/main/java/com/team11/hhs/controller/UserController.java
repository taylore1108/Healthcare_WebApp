package com.team11.hhs.controller;

import com.team11.hhs.model.User;
import com.team11.hhs.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
//    private static final Logger LOG = LoggerFactory.getLogger(TableController.class);
//    private final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
//    private final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
//    private final String TWILIO_MESSAGING_SERVICE_SID = System.getenv("TWILIO_MESSAGING_SERVICE_SID");
//    private final String PHONE_NUMBER = System.getenv("PHONE_NUMBER");

    public UserController(UserRepo quoteRepository) {
        this.userRepo = quoteRepository;
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    private UserRepo userRepo;


    @GetMapping("/user")
    public List<User> getUsers(@RequestParam("search") Optional<String> searchParam){
        return searchParam.map( param-> userRepo.getContainingFirstName(param) )
                .orElse(userRepo.findAll());
    }

    @GetMapping("/user/{userID}" )
    public ResponseEntity<String> readFirstName(@PathVariable("userID") Long id) {
        return ResponseEntity.of(userRepo.findById(id).map(User::getFirstname));
    }

    @GetMapping("/user/{userID}" )
    public ResponseEntity<String> readLastName(@PathVariable("userID") Long id) {
        return ResponseEntity.of(userRepo.findById(id).map(User::getLastname));
    }

    @GetMapping("/user/{userID}" )
    public ResponseEntity<String> readUserName(@PathVariable("userID") Long id) {
        return ResponseEntity.of(userRepo.findById(id).map(User::getUsername));
    }

    @GetMapping("/user/{userID}" )
    public ResponseEntity<String> readRole(@PathVariable("userID") Long id) {
        return ResponseEntity.of(userRepo.findById(id).map(User::getRole));
    }

    @PostMapping("/user")
    public User addUser(@RequestBody String username, @RequestBody String password, @RequestBody String firstName, @RequestBody String lastName, @RequestBody String role) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstname(firstName);
        newUser.setLastname(lastName);
        newUser.setRole(role);
        return userRepo.save(newUser);
    }

    @RequestMapping(value="/user/{userID}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "userID") Long id) {
        userRepo.deleteById(id);
    }
}
