package com.example.proooject.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Entity
@Slf4j
@NoArgsConstructor
@ToString
@Table(name = "lessons")
@Getter
@Setter
public class Lesson {
    public Lesson(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private int Id;
    @Column
    private String name;

    @Column
    private Date date;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "clientsonlessons",
    joinColumns = @JoinColumn(name = "lesson"),
    inverseJoinColumns = @JoinColumn(name = "client"))
    private List<Client> clientsOnLesson =new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "coaches_on_lessons",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id"))
    private List<Coach> coaches = new ArrayList<>();

    public void addClientToLesson(Client client){
        clientsOnLesson.add(client);
        client.getSubscription().visit();
        client.getLessons().add(this);

    }
    public void removeClientFromLesson(Client client){
        clientsOnLesson.remove(client);
        client.getLessons().remove(this);
    }
    public void addCoachToLesson(Coach coach){
        coach.getLessons().add(this);
        coaches.add(coach);
    }
    public void removeCoachFromLesson(Coach coach){
        coach.getLessons().remove(this);
        coaches.remove(coach);
    }
    public void showClients(){
      for(Client client: clientsOnLesson){
          log.info(client.toString());
      }
    }
    public void showCoaches(){
        for(Coach coach:coaches){
            log.info(coach.toString());
        }
    }
    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
