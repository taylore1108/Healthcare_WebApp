package com.team11.hhs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient")
public class Patient { //do we need a constructor?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private Long userid;

    @Column(nullable=false)
    private String dob;

    @Column(nullable=false)
    private int age;

    @Column(nullable=false)
    private String maritalStatus;

}
