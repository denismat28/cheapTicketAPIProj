package com.crazysusanin.planning.model;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "ticket_form")
@Data
public class TicketForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="departure_date")
    private String departDate;
    @Column(name="return_date")
    private String returnDate;
    @Column(name = "origin")
    private String origin;
    @Column(name = "destination")
    private String destination;
}
