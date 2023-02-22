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

    @Column(name = "userUserName")
    private String userUserName;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "userRole")
    private String userRole;

    @Column(name = "userDateEnrolled")
    private String userDateEnrolled;

    @Column(name = "userFirstName")
    private String userFirstName;

    @Column(name = "userLastName")
    private String userLastName;

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserUserName() {
        return userUserName;
    }

    public void setUserUserName(String userUserName) {
        this.userUserName = userUserName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserDateEnrolled() {
        return userDateEnrolled;
    }

    public void setUserDateEnrolled(String userDateEnrolled) {
        this.userDateEnrolled = userDateEnrolled;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}