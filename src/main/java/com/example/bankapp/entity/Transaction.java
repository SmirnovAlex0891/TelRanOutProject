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
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//@GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne()
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
    private Account debitAccountId;
    @ManyToOne()
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
    private Account creditAccountId;

}
