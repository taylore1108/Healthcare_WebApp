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
@Entity(name="bills")
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean Paid = false;

    @Column(nullable=false)
    private String patientUsername;

    @Column
    private String ProcedureName;

    @Column(nullable=false)
    private int billPrice;

    public Bills(boolean paid, String patientUsr, String procedureName, int billPrice) {
        Paid = paid;
        patientUsername = patientUsr;
        ProcedureName = procedureName;
        this.billPrice = billPrice;
    }
}
