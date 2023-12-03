package com.example.proooject.Contollers.Administration;

import com.example.proooject.Model.User;
import com.example.proooject.Repository.UserRepository;
import com.example.proooject.Repository.SubscriptionRepository;
import com.example.proooject.Service.SubscriptionService;
import com.example.proooject.Service.UserService;
import com.example.proooject.Utility.StringToDateParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Calendar;

@Controller
@RequestMapping("/Admin/subCreate")
@Slf4j
public class CreateSubscriptionController {
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
    private SubscriptionService subscriptionService;
    private UserService userService;
    @Autowired
    public CreateSubscriptionController(UserRepository userRepository, SubscriptionRepository subscriptionRepository, SubscriptionService subscriptionService, UserService userService) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @GetMapping
    public String createSubscription(Model model){
        userService.getClientList();
        model.addAttribute("users",userService.getClientList());
        return "subcreate";
    }

    @PostMapping
    public String createSubscriptionPost(@RequestParam("expirationDate") String date
    ,@RequestParam("visits") Integer visits
    ,@RequestParam("user_id") Integer user_id) throws ParseException {
        Calendar expirationDate = StringToDateParser.parse(date);
        User user = userRepository.findById(user_id).get();
        subscriptionService.saveSubscription(visits,expirationDate,user);
        return "redirect:/Admin/subCreate";
    }
}
