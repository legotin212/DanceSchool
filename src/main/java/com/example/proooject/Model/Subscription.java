package com.example.proooject.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;

@Entity
@Slf4j
@Table(name = "subscription")
@Getter
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name ="expiration_date")
    private Date expirationDate;
    @OneToOne
    @MapsId
    @Setter
    private User user;
    @Column
    private int visits;

    public Subscription(Date expirationDate, int visits) {
        this.expirationDate = expirationDate;
        this.visits = visits;
    }

    public void visit(){
        this.visits-=1;
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
