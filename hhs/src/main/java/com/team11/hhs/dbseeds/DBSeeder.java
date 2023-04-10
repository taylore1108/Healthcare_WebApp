//package com.team11.hhs.dbseeds;
//
//import com.team11.hhs.DTO.UserDTO;
//import com.team11.hhs.model.User;
//import com.team11.hhs.repository.RoleRepo;
//import com.team11.hhs.repository.UserRepo;
//import com.team11.hhs.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//
//@Component
//public class DBSeeder implements CommandLineRunner {
//    @Autowired
//    private UserRepo userRepo;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RoleRepo roleRepo;
//
//    @Override
//    public void run(String... args) throws Exception {
//        seedDB();
//    }
//
//    private void seedDB(){
//        ArrayList<User> users = new ArrayList<User>();
//        User testUser = new User("user1", "Hello123!", "John", "Wick");
//        users.add(testUser);
//        for (User user : users) {
//            if (userRepo.findByUsername(user.getUsername()) == null) {
//                UserDTO userDTO = new UserDTO();
//                userService.saveUser(userDTO);
//            }
//        }
//
//    }
//}
