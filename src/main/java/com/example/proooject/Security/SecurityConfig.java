package com.example.proooject.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
//@Bean
//public UserDetailsService userDetailsService() {
//    UserDetails userDetails = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//
//    return new InMemoryUserDetailsManager(userDetails);
//}
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);


        return new ProviderManager(authenticationProvider);
    }

//    @Bean
//    public AuthorityAuthorizationManager authorityAuthorizationManager(){
//        authorityAuthorizationManager().setRoleHierarchy();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/dt").hasRole("USER");
                    auth.requestMatchers("/pc/selectLesson").hasRole("USER");
                    auth.requestMatchers("/pc").hasRole("USER");
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/Admin").hasRole("ADMIN");
                    auth.requestMatchers("/coach").hasRole("COACH");
                    auth.requestMatchers("/subcreate").hasRole("USER");
                    auth.requestMatchers("/selectLesson").hasRole("USER");
                    auth.requestMatchers("/admin").hasRole("ADMIN");
                    auth.requestMatchers("/admin/deleteUser").hasRole("ADMIN");
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }
    @Bean
    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("ROlE_");
    }
    @Bean
    static RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_COACH\n" +
                "ROLE_COACH > ROLE_USER\n");
        return hierarchy;
    }
    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }


}
