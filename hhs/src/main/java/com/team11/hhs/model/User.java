package com.team11.hhs.model;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity(name = "user")
public class User { //do we need a constructor?
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "dateEnrolled")
    private String dateEnrolled;

    @Column(name = "userFirstName")
    private String firstname;

    @Column(name = "userLastName")
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String userRole) {
        this.role = userRole;
    }

    public String getDateEnrolled() {
        return dateEnrolled;
    }

    public void setDateEnrolled(String dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String userLastName) {
        this.lastname = userLastName;
    }

    public String printUser(){
        return Arrays.toString((new String[]{username, password, firstname, lastname, role}));
    }
}