package com.team11.hhs.controller;

import com.team11.hhs.model.User;
import com.team11.hhs.service.UserService;
import com.team11.hhs.DTO.UserDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Default always lands on index page
    @GetMapping(path = "/")
    public String index() {
        return "redirect:/index";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByUsername(user.getUsername());
        if (existing != null) {
            result.rejectValue("username", null, "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("DOCTOR"))) {
            return "hello";
        } else {
            List<UserDTO> users = userService.findAllUsers();
            model.addAttribute("users", users);
            return "users";
        }
    }

    @GetMapping("/doctorHome")
    public String checkDoctorLogin(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("DOCTOR"))) {
            return "redirect:/doctor/home";
        } else {
            List<UserDTO> users = userService.findAllUsers();
            model.addAttribute("users", users);
            return "redirect:/doctor/home";
        }
    }

    @GetMapping("/schedule")
    public String getSchedule(Model model){
        return "schedule";
    }
}
