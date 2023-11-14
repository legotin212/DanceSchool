//package com.example.proooject.Contollers;
//
//import com.example.proooject.Model.User;
//import com.example.proooject.Service.UserService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/registration")
//public class RegistrationController {
//    private UserService userService;
//    @Autowired
//    public RegistrationController(UserService userService) {
//        this.userService = userService;
//    }
//    @GetMapping
//    public String registration(Model model){
//        model.addAttribute("userForm",new User());
//        return "registration";
//    }
//    @PostMapping
//    public String addClient(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model){
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "registration";
//        }
//        if (!userService.saveUser(userForm)){
//            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//            return "registration";
//        }
//
//        return "redirect:/";
//    }
//
//}
