package com.example.proooject.Contollers.Coaches;

import com.example.proooject.Model.User;
import com.example.proooject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@Controller
public class CoachController {

    UserService userService;
    @Autowired
    public CoachController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/Coach")
    public String clientController(Model model){
        User user =  userService.getCurrentUser();
        model.addAttribute("user",user);
        model.addAttribute("lessons",user.getLessonsAsCoach());

        return "coach";

    }
}
