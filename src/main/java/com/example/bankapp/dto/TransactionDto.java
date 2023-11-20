package com.example.bankapp.dto;

import lombok.Value;

@Value
public class TransactionDto {
    String type;
    Double amount;
    String description;
    String createdAt;
    String debitAccountName;
    String debitClientFirstName;
    String debitClientLastName;
    String creditAccountName;
    String creditAccountFirstName;
    String creditAccountLastName;
}
