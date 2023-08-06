package com.example.bankapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;
    @Column(name = "type")
    private String type;
    @Column(name = "amount")
    private double amount;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private Timestamp createdAt;
}
