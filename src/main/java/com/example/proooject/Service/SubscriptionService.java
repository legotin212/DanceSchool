package com.example.proooject.Service;

import com.example.proooject.Model.Subscription;
import com.example.proooject.Model.User;
import com.example.proooject.Repository.SubscriptionRepository;
import com.example.proooject.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
@Slf4j
@Service
public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository;
    private UserRepository userRepository;
    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public void giveSubscriptionToUser(Subscription subscription, User user){
        subscription.setUser(user);
        user.setSubscription(subscription);
        userRepository.save(user);
        subscriptionRepository.save(subscription);
    }
    public void saveSubscription(Integer visits, Calendar expirationDate,User user){
        Subscription subscription = new Subscription(expirationDate,visits);
        giveSubscriptionToUser(subscription,user);

    }
}
