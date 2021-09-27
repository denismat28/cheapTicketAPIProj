package com.crazysusanin.planning.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "transfer_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaTicketInfo {

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



    public AviaTicketInfo(String departDate, String returnDate, String origin, String destination) {
        this.departDate = departDate;
        this.returnDate = returnDate;
        this.origin = origin;
        this.destination = destination;
    }

    //constructor for parsing to POJO
    @JsonCreator
    public AviaTicketInfo( @JsonProperty("price") double price,@JsonProperty("airline") String airline,@JsonProperty("flight_number") int flightNumber,
                           @JsonProperty("expires_at") String expiresAt,@JsonProperty("departure_at") String departDate,@JsonProperty("return_at") String returnDate) {
        this.price = price;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.expiresAt = expiresAt;
        this.departDate = departDate;
        this.returnDate = returnDate;
    }
}
