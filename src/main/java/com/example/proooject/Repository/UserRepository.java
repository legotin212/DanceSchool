package com.example.proooject.Repository;

import com.example.proooject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
