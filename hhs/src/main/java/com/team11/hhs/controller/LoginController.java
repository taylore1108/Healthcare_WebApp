package com.team11.hhs.controller;

import com.team11.hhs.service.LoginService;
import com.team11.hhs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
    @Autowired
    LoginService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){
        boolean isValidUser = service.validateUser(name, password);

        if (!isValidUser){
            model.put("errorMessage", "Incorrect Login Info");
            return "login_page";
        }

        model.put("name", name);
        model.put("password", password);

        return "welcome";
    }
    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(){
        return "login_page";
    }*/

}
