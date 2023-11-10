package com.example.proooject.Contollers;

import com.example.proooject.Model.Client;
import com.example.proooject.Model.Subscription;
import com.example.proooject.Repository.ClientRepository;
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
    private ClientRepository clientRepository;
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    public CreateSubscriptionController(ClientRepository clientRepository, SubscriptionRepository subscriptionRepository) {
        this.clientRepository = clientRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping
    public String createSubscription(){
        Client client =
                clientRepository.findById("5").get();
        Subscription subscription = new Subscription(Date.valueOf("2023-12-09"),12);
        client.setSubscription(subscription);
        subscriptionRepository.save(subscription);
        clientRepository.save(client);

        log.info(subscription.toString());
        return "subcreate";
    }
}
