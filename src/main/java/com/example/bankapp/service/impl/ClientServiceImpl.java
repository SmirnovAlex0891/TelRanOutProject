package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.mapper.ClientMapper;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.service.ClientService;
import com.example.bankapp.service.exception.ClientNotFoundException;
import com.example.bankapp.service.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    @Override
    public ClientDto getClientById(Long id) {
        return clientMapper.clientToClientDto(clientRepository.findById(id).orElseThrow(() ->
                new ClientNotFoundException(String.format(
                        ErrorMessage.CLIENT_NOT_FOUND, id
                ))));
    }
    @Override
    public void deleteClientById(Long id) {
        checkClientById(id);
        clientRepository.deleteById(id);
    }
    private void checkClientById(Long id) {
        clientRepository.findById(id).orElseThrow(() ->
                new ClientNotFoundException(String.format(
                        ErrorMessage.CLIENT_NOT_FOUND, id
                )));
    }
}
