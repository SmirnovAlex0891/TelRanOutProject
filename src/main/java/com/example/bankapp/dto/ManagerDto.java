package com.example.bankapp.dto;

import lombok.Value;

import java.util.Set;

@Value
public class ManagerDto {
    String firstName;
    String lastName;
    String status;
    String createdAt;
    String updatedAt;
    Set<ClientAndAccountBalanceDto> clients;
}
