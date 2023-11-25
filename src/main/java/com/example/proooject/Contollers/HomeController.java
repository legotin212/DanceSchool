package com.example.proooject.Contollers;

import com.example.proooject.Model.User;
import com.example.proooject.Repository.UserRepository;
import com.example.proooject.Repository.RoleRepository;
import com.example.proooject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@SessionAttributes("client")
public class HomeController {

    private UserRepository userRepository;
    private UserService userService;
    private RoleRepository roleRepository;
    @Autowired
    public HomeController(UserRepository userRepository, UserService userService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @ModelAttribute
    public User client(){
        return new User();
    }
    @GetMapping("/")
    public String homePage(Model model){
        return "home";
    }


}
