package com.example.bankapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "agreements")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//@GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private Long id;
    @Column(name = "interest_rate")
    private double interestRate;
    @Column(name = "status")
    private String status;
    @Column(name = "sum")
    private double sum;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;
}
