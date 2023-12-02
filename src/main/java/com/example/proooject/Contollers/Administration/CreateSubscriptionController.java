package com.example.proooject.Contollers.Administration;

import com.example.proooject.Model.User;
import com.example.proooject.Model.Subscription;
import com.example.proooject.Repository.UserRepository;
import com.example.proooject.Repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Controller
@RequestMapping("/subcreate")
@Slf4j
public class CreateSubscriptionController {
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    public CreateSubscriptionController(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping
    public String createSubscription(){
        return "subcreate";
    }
}
