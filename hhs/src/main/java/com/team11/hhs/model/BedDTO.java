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
@Entity(name="bedsDTO")
public class BedDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //todo add error statement
    @Column(nullable=false, unique=true)
    private String name;

    @Column(nullable=false, unique=true)
    private String username;
}
