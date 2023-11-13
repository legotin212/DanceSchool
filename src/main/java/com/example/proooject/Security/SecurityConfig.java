package com.example.proooject.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();}

//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authz -> authz
//                .requestMatchers("/authentication/**").permitAll()
//                .requestMatchers("/h2/**").permitAll()
//                .anyRequest().authenticated());
//        return http.build();
//
//    }
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
//        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector).servletPath("/path");
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers(mvcMatcherBuilder.pattern("/selectLesson")).hasRole("USER")
//                        .requestMatchers(mvcMatcherBuilder.pattern("/user")).permitAll()
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/selectLesson").hasRole("USER");
                    auth.requestMatchers("/admin").hasRole("Admin");
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }

}
