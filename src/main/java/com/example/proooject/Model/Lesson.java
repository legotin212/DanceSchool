package com.example.proooject.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

@Entity
@Slf4j
@NoArgsConstructor

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
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @Column
    private boolean isExpired;

    @Override
    public String toString() {
        return "Lesson{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", isExpired=" + isExpired +
                '}';
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "user_on_lessons",
    joinColumns = @JoinColumn(name = "lesson"),
    inverseJoinColumns = @JoinColumn(name = "user"))
    private Set<User> usersOnLesson =new HashSet<>();
    @ManyToMany( cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "coaches_on_lessons",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id"))
    private Set<User> coachesOnLesson = new HashSet<>();

    public Lesson(String name, Calendar date) {
        this.name = name;
        this.date = date;
    }


    public Lesson(String name, Calendar date, boolean isExpired) {
        this.name = name;
        this.date = date;
        this.isExpired = isExpired;
    }
    public boolean hasUser(User user){
        log.info(String.valueOf(usersOnLesson.size()));
        if (usersOnLesson.contains(user)){


            log.info("true");
            return true;
        }
        log.info("false");
        return false;
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
