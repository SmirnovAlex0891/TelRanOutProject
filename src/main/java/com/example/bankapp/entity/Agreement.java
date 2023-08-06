package com.example.bankapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "agreements")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agreement {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;
    @Column(name = "interest_rate")
    private double interestRate;
    @Column(name = "status")
    private String status;
    @Column(name = "sum")
    private double sum;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
