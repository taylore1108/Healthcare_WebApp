package com.team11.hhs.controller;

import com.team11.hhs.model.Bed;
import com.team11.hhs.model.BedDTO;
import com.team11.hhs.model.User;
import com.team11.hhs.service.BedService;
import com.team11.hhs.service.UserService;
import com.team11.hhs.DTO.UserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
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
    private BedService bedService;

    public AuthController(UserService userService, BedService bedService){
        this.userService = userService;
        this.bedService = bedService;
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

    @GetMapping("/reset")
    public String showResetPassword(Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "reset";
    }

    @GetMapping("/reset/redirect")
    public String moveToResetPassword(Model model){
        return "redirect:/reset";
    }


    @PostMapping("/reset/password")
    public String resetPassword(@Valid @ModelAttribute("user") UserDTO user,
                                BindingResult result,
                                Model model){
        User existing = userService.findByUsername(user.getUsername());
        if (existing == null){
            result.rejectValue("username", null, "There is no account registered with that username");
            model.addAttribute("user", user);
            return "reset";
        }
        if(existing.getPassword().equals(user.getPassword())){
            result.rejectValue("password", null, "New password cannot be the same as the old password");
        }
        if(!user.getPassword().equals(user.getPassword2())){
            result.rejectValue("password2", null, "Passwords do not match");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "reset";
        }
//        userService.saveUser(user);
        return "redirect:/reset?success";
    }

    @GetMapping("bed")
    public String showBedForm(Model model){
        Bed bed = new Bed();
        model.addAttribute("bed", bed);
        List<Bed> beds = bedService.findAllBeds();
        model.addAttribute("beds", beds);
        return "bed";
    }

    @PostMapping("/bed/save")
    public String addBed(@Valid @ModelAttribute("bed") Bed bed,
                               BindingResult result,
                               Model model){
        Bed existing = bedService.findByBedName(bed.getName());
        if (existing != null) {
            result.rejectValue("name", null, "There is already an bed registered with that name");
        }
        if (result.hasErrors()) {
            model.addAttribute("bed", bed);
            return "bed";
        }
        bedService.saveBed(bed);
        return "redirect:/bed?success";
    }
    @PostMapping("/bed/update")
    public String addBed(@Valid @ModelAttribute("bedDTO") BedDTO bed,
                         BindingResult result,
                         Model model){
        Bed existing = bedService.findByBedName(bed.getName());
        if (existing == null) {
            result.rejectValue("name", null, "There is not a bed registered with that name");
        }
        if (result.hasErrors()) {
            model.addAttribute("bedDTO", bed);
            return "bed";
        }

        bedService.updateBed(bed);

        return "redirect:/bed?success";
    }
}
