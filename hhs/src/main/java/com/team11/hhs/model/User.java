package com.team11.hhs.model;

import jakarta.persistence.*;
@Entity(name = "user")
public class User {
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long id;

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Column(name = "userFirstName")
    private String userFirstName;

}