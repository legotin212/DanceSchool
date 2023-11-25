//package com.example.proooject.Contollers;
//
//import com.example.proooject.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping
//public class fromguide {
//    private UserService userService;
//    @Autowired
//    public fromguide(UserService userService) {
//        this.userService = userService;
//    }
//    @GetMapping("/admin")
//    public String clientList(Model model){
//        model.addAttribute("allClients", userService.allUser());
//        return "admin";
//    }
//        @PostMapping("/admin")
//        public String deleteClient(@RequestParam(required = true, defaultValue = "" ) int clientId,
//                @RequestParam(required = true, defaultValue = "" ) String action,
//                Model model){
//        if (action.equals("delete")){
//            userService.deleteClient(clientId);
//        }
//        return "redirect:/admin";
//        }
//        @GetMapping("/admin/gt/{clientId}")
//        public String gtClient(@PathVariable("clientId") int clientId, Model model){
//            model.addAttribute("allClients", userService.UserGetList(clientId));
//            return "admin";
//
//        }
//    }
//
