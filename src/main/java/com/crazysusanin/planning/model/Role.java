package com.crazysusanin.planning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String type;
    private String description;


    public Role(int id) {
        this.id = id;
    }

    public Role(String type) {
        this.type = type;
    }

}