package com.example.proooject.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String lastname;
    @ManyToMany(mappedBy = "usersOnLesson")
    private  Set<Lesson> lessons = new HashSet<>();
    @ManyToMany(mappedBy = "coachesOnLesson")
    private Set<Lesson> lessonsAsCoach = new HashSet<>();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Subscription subscription;
    @ManyToMany(fetch=FetchType.EAGER,mappedBy = "users")
    private Set<Role> roles = new HashSet<>();
    @Column
    private String password;
    @Column(name = "login")
    private String username;
    @Transient
    private String passwordConfirm;
    public User(String name, String lastname, String password, String username) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.username = username;
    }
    @PreRemove
    public void removeUserAssociations(){
        for(Lesson lesson: this.getLessons()){
            lesson.removeClient(this);
        }
        for (Lesson lesson: this.getLessonsAsCoach()){
            lesson.removeCoach(this);
        }
        for(Role role: this.getRoles()){
            role.removeUser(this);
        }
    }


    public void addRole(Role role){
        roles.add(role);
    }
    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }
    public void addLessonAsCoach(Lesson lesson){
        lessonsAsCoach.add(lesson);
    }
    public void removeLesson(Lesson lesson){
        lessons.remove(lesson);
    }
    public void removeLessonAsCoach(Lesson lesson){
        lessonsAsCoach.remove(lesson);
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
