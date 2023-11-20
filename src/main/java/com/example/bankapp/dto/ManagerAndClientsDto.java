package com.example.bankapp.dto;

import com.example.bankapp.entity.Client;
import lombok.Value;

import java.util.Set;

@Value
public class ManagerAndClientsDto {
    String firstName;
    String lastName;
    String status;
    String createdAt;
    String updatedAt;
    Set<Client> clients;
}
