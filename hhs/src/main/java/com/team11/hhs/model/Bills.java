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
    private Date DateOfAppt;

    @Column
    private boolean Paid = false;

    @Column(nullable=false, unique=true)
    private Long PatientID;

    @Column(nullable=false, unique=true)
    private Long ScheduleTypeID;
}
