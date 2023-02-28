package com.team11.hhs.controller;

//import com.team11.hhs.DTO.UserDTO;
import com.team11.hhs.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

    @Controller
public class HhsApplicationController {
        @GetMapping(path = "/")
        public String index() {
            return "redirect:/login";
        }

        @GetMapping(path = "/login")
        public String login(Model model) {
            //This will be used to compare username & password to the database
            //model.addAttribute("userDTO", new UserDTO());
            return "login";
        }
    }
