package com.example.bankapp.dto;

import lombok.Value;

import java.util.Set;

@Value
public class AccountDto {
    Long id;
    String name;
    String type;
    String status;
    Double balance;
    String currencyType;
    String createdAt;
    String updatedAt;
    String clientLastName;
    Long agreementId;
    Set<Long> creditTransactions;
    Set<Long> debitTransactions;
}
