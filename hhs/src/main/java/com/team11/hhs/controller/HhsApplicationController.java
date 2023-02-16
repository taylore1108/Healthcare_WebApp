package com.team11.hhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HhsApplicationController {
    @GetMapping(path = "/helloWorld")
    @ResponseBody
    public String helloWorld(){
        return "Hello World";
    }
}