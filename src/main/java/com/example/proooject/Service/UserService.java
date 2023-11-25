package com.example.proooject.Service;

import com.example.proooject.Model.User;
import com.example.proooject.Model.Role;
import com.example.proooject.Repository.UserRepository;
import com.example.proooject.Repository.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j

@Service
public class UserService implements UserDetailsService {

    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private LessonService lessonService;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, LessonService lessonService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.lessonService = lessonService;
    }
    public boolean saveNewUser(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        Role role = roleRepository.findById(1).get();

        role.addUser(user);
        user.addRole(role);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        roleRepository.save(role);

        return true;
    }

    public boolean saveNewUserAsCoach(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null){
            return false;
        }
        Role role = roleRepository.findById(3).get();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.addRole(role);
        role.addUser(user);
        roleRepository.save(role);
        userRepository.save(user);

        return true;
    }


//    public User findUserById(int clientId){
//        Optional<User> clientFromDb = userRepository.findById(clientId);
//        return clientFromDb.orElse(new User());
//    }

    public List<User> allUser(){
        return userRepository.findAll();
    }
    public boolean deleteUser(int clientId){
        if(userRepository.findById(clientId).isPresent()){
            userRepository.deleteById(clientId);
            return true;
        }
        return false;
    }

    @Transactional
    public void deleteUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        userRepository.delete(user);
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth  != null && auth .isAuthenticated()) {
            User user = (User) auth .getPrincipal();
            return user;
        } else {
            return null;
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    public List<User> GetClientList() {
        return entityManager.createQuery(
                        "SELECT u FROM User u LEFT JOIN u.roles r WHERE r.id = :paramId",
                        User.class
                )
                .setParameter("paramId", 1)
                .getResultList();
    }
    public List<User> GetAdminList() {
        return entityManager.createQuery(
                        "SELECT u FROM User u LEFT JOIN u.roles r WHERE r.id = :paramId",
                        User.class
                )
                .setParameter("paramId", 2)
                .getResultList();
    }
    public List<User> GetCoachList() {
        return entityManager.createQuery(
                        "SELECT u FROM User u LEFT JOIN u.roles r WHERE r.id = :paramId",
                        User.class
                )
                .setParameter("paramId", 3)
                .getResultList();
    }
}
