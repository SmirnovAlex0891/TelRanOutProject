package com.example.bankapp.service;

import com.example.bankapp.dto.ClientDto;

public interface ClientService {
    ClientDto getClientById(Long id);
    void deleteClientById(Long id);
}
