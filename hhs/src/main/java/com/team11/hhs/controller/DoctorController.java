package com.team11.hhs.controller;

import com.team11.hhs.model.MedicalProcedure;
import com.team11.hhs.service.MedicalProcedureService;
import com.team11.hhs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@RequestMapping("/doctor")
public class DoctorController {

    private UserService userService;

    @Autowired
    private MedicalProcedureService medicalProcedureService;

    @GetMapping("/home")
    public String doctorHome(Model model){
        return "doctorHome";
    }

//    @PostMapping("/doctorHome")
//    public String getDoctorSchedule(Model model){
//        return "redirect:/doctor/schedule";
//    }

    @GetMapping("/doctorSchedule")
    public String viewDoctorSchedule(Model model) {
        return "schedule";
    }

//    @GetMapping(path = "/billing")
//    public String adminInventoryPage(Model model) {
//        model.addAttribute("medicalProcedures", new MedicalProcedure());
//        List<MedicalProcedure> procedures = medicalProcedureService.getAllProcedures();
//        model.addAttribute("procedures", procedures);
//        return "billing";
//    }
}
