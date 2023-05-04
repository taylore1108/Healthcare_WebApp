package com.team11.hhs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "MedicalProcedureTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String procedureName;
    @Column
    private int procedureCost;

}
