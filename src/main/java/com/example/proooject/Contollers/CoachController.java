//package com.example.proooject.Contollers;
//
//import com.example.proooject.Model.User;
//import com.example.proooject.Service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//@Slf4j
//@Controller
//public class CoachController {
//    @Autowired
//    UserService userService;
//
//    @GetMapping("/coach}")
//    public String clientController(Model model){
//        User user =  userService.getCurrentUser();
//        model.addAttribute("name",user.getName());
//        model.addAttribute("lastname",user.getLastname());
//        model.addAttribute("lessons",user.getLessonsAsCoach());
//
//        log.info(user.toString());
//        return "admin";
//
//    }
//}
