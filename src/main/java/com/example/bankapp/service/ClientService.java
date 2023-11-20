package com.example.bankapp.service;

import com.example.bankapp.dto.ClientCreateDto;
import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.entity.Client;

import java.util.List;

public interface ClientService {
    ClientDto getClientById(Long id);
    void deleteClientById(Long id);
}
