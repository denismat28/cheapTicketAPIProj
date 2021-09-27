package com.crazysusanin.planning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "aviaticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AviaTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="price")
    private double price;

    @Column(name="airline")
    private String airline;

    @Column(name="flight_number")
    private int flightNumber;

    @Column(name="expires_at")
    private String expiresAt;

    @Column(name="departure_at")
    private String departDate;

    @Column(name="return_at")
    private String returnDate;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


}
