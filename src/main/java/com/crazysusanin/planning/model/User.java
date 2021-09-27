package com.crazysusanin.planning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String email;
    private String password;


    @Column(name = "passwordConfirm")
    private String passwordConfirm;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;



    @Column(name = "IS_ACTIVE")
    private boolean Active;

    public User(int id) {
        this.id = id;
    }

    public User(String name, String email) {
        this.username = name;
        this.email = email;
    }

    public User(int id, String username, String email, String password, String passwordConfirm) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public boolean getIsActive() {
        return isActive();
    }



    public boolean isActive() {
        return true;
    }
}