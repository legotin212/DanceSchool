package com.example.proooject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Slf4j
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Client {
    public Client(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String name;
    @Column
    @NotNull
    private String lastname;

    @ManyToMany(mappedBy = "clientsOnLesson")
    private List<Lesson> lessons = new ArrayList<>();

    @OneToOne(mappedBy = "client")

    private Subscription subscription;


    public void setSubscription(Subscription subscription){
        subscription.setClient(this);
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
}
