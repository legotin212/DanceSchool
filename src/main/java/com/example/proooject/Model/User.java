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
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = {"name","username","lastname","id"})
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String lastname;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "usersOnLesson")
    private  Set<Lesson> lessons = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "coachesOnLesson")
    private Set<Lesson> lessonsAsCoach = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER,mappedBy = "user", cascade = CascadeType.MERGE)
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                '}';
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

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void addRole(Role role){
        roles.add(role);
    }
    public void addLesson(Lesson lesson){
        log.info("method add lesson");
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
