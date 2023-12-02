package com.example.proooject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class ProoOjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProoOjectApplication.class, args);
    }

}
