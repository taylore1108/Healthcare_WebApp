package com.team11.hhs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="bills")
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean Paid = false;

    @Column(nullable=false, unique=true)
    private String PatientUsr;

    @Column
    private String ProcedureName;

    @Column(nullable=false, unique=true)
    private int billPrice;

    public Bills(boolean paid, String patientUsr, String procedureName, int billPrice) {
        Paid = paid;
        PatientUsr = patientUsr;
        ProcedureName = procedureName;
        this.billPrice = billPrice;
    }
}
