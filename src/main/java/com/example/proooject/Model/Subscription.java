package com.example.proooject.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.Calendar;

@Entity
@Slf4j
@Table(name = "subscription")
@Getter
@Setter
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name ="expiration_date")
    private Calendar expirationDate;
    @OneToOne
    @MapsId
    private User user;
    @Column
    private Integer visits;

    public Subscription(Calendar expirationDate, Integer visits) {
        this.expirationDate = expirationDate;
        this.visits = visits;
    }

    public void visit(){
        if(visits!=null){
       visits-=1;}
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "expirationDate=" + expirationDate +
                ", client=" + user.toString() +
                ", visits=" + visits +
                '}';
    }
}
