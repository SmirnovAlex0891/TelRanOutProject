package com.example.bankapp.dto;

import lombok.Value;

import java.util.Set;

@Value
public class ClientDto {
    Long id;
    String status;
    String taxCode;
    String firstName;
    String lastName;
    String email;
    String address;
    String phone;
    String createdAt;
    String updatedAt;
    String managerLastName;
    Set<String> accounts;
}