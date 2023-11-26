package com.example.proooject.Service;

import com.example.proooject.Model.Subscription;
import com.example.proooject.Model.User;
import com.example.proooject.Repository.SubscriptionRepository;
import com.example.proooject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
