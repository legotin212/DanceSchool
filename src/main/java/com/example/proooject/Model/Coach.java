package com.example.proooject.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@Slf4j
@NoArgsConstructor
@Getter
@Table(name = "coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String lastname;
    @ManyToMany(mappedBy = "coaches")
    private List<Lesson> lessons = new ArrayList<>();

    public Coach(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public void showLessons() {
        for(Lesson lesson : lessons){
            log.info(lesson.toString());
    }
    }
    @Override
    public String toString() {
        return "Coach{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
