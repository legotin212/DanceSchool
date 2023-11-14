package com.example.proooject.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Slf4j
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {

    public User(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String lastname;
    @ManyToMany(mappedBy = "usersOnLesson")
    private List<Lesson> lessons = new ArrayList<>();
    @OneToOne(mappedBy = "user")
    private Subscription subscription;
    @Column
    @Setter
    @Getter
    private String password;
    @Column(name = "login")
    @Setter
    private String username;

    @Transient
    private String passwordConfirm;
    @ManyToMany(fetch=FetchType.EAGER,mappedBy = "users")
    private List<Role> roles = new ArrayList<>();


    public void setSubscription(Subscription subscription){
        subscription.setUser(this);
        this.subscription=subscription;
    }
    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
    public void showLessons(){
        for(Lesson lesson : lessons){
            log.info(lesson.toString());
        }
    }
    public void addRole(Role role){
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
