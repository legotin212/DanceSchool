package com.example.proooject.Contollers.Administration;

import com.example.proooject.Model.User;
import com.example.proooject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/Admin")
public class AdminController {
    private UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String regPage( Model model){
        model.addAttribute("coaches",userService.getCoachList());
        model.addAttribute("users",userService.getClientList());
        model.addAttribute("admin",userService.getAdminList());
        return "admin";

    }
    @PostMapping
    public String regUser(@RequestParam("name") String name
    ,@RequestParam("lastname") String lastname
    ,@RequestParam("password") String password
    ,@RequestParam("username") String username){
        userService.saveNewUser(new User(name,lastname,password,username));
      return "redirect:/Admin";
    }

//    @PostMapping("/reg")
//    public String regCoach(@RequestParam("cname") String name
//            ,@RequestParam("clastname") String lastname
//            ,@RequestParam("cpassword") String password
//            ,@RequestParam("cusername") String username){
//        userService.saveUserAsCoach(new User(name,lastname,password,username));
//        return "reg";
//    }
    @DeleteMapping()
    public String deleteUser(@RequestParam String usernamefordelete){
        userService.deleteUserByUsername(usernamefordelete);
        return "redirect:/Admin";
    }
}
