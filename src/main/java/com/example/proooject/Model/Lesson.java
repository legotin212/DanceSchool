package com.example.proooject.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Entity
@Slf4j
@NoArgsConstructor
@ToString
@Table(name = "lessons")
@Getter
@Setter
public class Lesson {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column
    private String name;
    @Column
    private Date date;

    @Column
    private boolean isExpired;

    @ManyToMany( cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "user_on_lessons",
    joinColumns = @JoinColumn(name = "lesson"),
    inverseJoinColumns = @JoinColumn(name = "user"))
    private Set<User> usersOnLesson =new HashSet<>();
    @ManyToMany( cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "coaches_on_lessons",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id"))
    private Set<User> coachesOnLesson = new HashSet<>();

    public Lesson(String name, Date date) {
        this.name = name;
        this.date = date;
    }
    public void addClient(User client){
        usersOnLesson.add(client);
    }
    public void addCoach(User coach){
        coachesOnLesson.add(coach);
    }
    public void removeClient(User client){
        usersOnLesson.remove(client);
        client.getLessons().remove(this);
    }
    public void removeCoach(User coach){
        usersOnLesson.remove(coach);
        coach.getLessonsAsCoach().remove(this);
    }


}
