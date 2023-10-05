package com.example.bankapp.service;

import com.example.bankapp.dto.ClientCreateDto;
import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.entity.Client;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();

    ClientDto addNewClient(ClientCreateDto clientCreateDto);
    ClientDto getClientById(Long id);
    void deleteClientById(Long id);
}
