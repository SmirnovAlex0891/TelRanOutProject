package com.example.bankapp.dto;

import lombok.Value;

@Value
public class ClientAndAccountBalanceDto {
    String clientFirstName;
    String clientLastName;
    String accountName;
    String accountBalance;
}
