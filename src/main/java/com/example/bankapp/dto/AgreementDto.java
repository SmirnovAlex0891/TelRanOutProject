package com.example.bankapp.dto;

import lombok.Value;

@Value
public class AgreementDto {
    String accountName;
    String accountStatus;
    String clientName;
    String productName;
    String interestRate;
    String agreementStatus;
    String agreementSum;
    String accountBalance;
}
