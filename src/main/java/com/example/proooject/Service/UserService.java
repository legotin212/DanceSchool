package com.example.proooject.Service;

import com.example.proooject.Model.User;
import com.example.proooject.Model.Role;
import com.example.proooject.Repository.UserRepository;
import com.example.proooject.Repository.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.entityManager = entityManager;
    }


    public boolean saveUser(User user){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null){
            return false;
        }
        Role role = roleRepository.findById(2).get();
        user.addRole(role);
        role.addUser(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }

    public User findClientById(int clientId){
        Optional<User> clientFromDb = userRepository.findById(clientId);
        return clientFromDb.orElse(new User());
    }

    public List<User> allUser(){
        return userRepository.findAll();
    }
    public boolean deleteClient(int clientId){
        if(userRepository.findById(clientId).isPresent()){
            userRepository.deleteById(clientId);
            return true;
        }
        return false;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public List<User> UserGetList(int idMin) {
        return entityManager.createQuery("SELECT c FROM User c WHERE c.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

}
