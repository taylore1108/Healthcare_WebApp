package com.team11.hhs.controller;

import com.team11.hhs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private UserService userService;

    @GetMapping("/home")
    public String doctorHome(Model model){
        return "doctorHome";
    }
}
