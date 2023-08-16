package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ClientCreateDto;
import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.entity.Client;
import com.example.bankapp.entity.Manager;
import com.example.bankapp.mapper.ClientMapper;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ClientService;
import com.example.bankapp.service.exception.ClientAlreadyExistException;
import com.example.bankapp.service.exception.ErrorMessage;
import com.example.bankapp.service.exception.ManagerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ManagerRepository managerRepository;

    @Override
    public List<ClientDto> getAllClients() {
        return clientMapper.toDtoList(clientRepository.findAll());
    }

    @Override
    public ClientDto addNewClient(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.createClientEntity(clientCreateDto);
        client.setStatus("ACTIVE");
        checkClientNotExist(client);
        client.setManager(searchManagerByLastname(clientCreateDto));
        return clientMapper.clientToClientDto(clientRepository.save(client));
    }

    private void checkClientNotExist(Client client) {
        clientRepository.findAll().forEach(c -> {
            if (c.equals(client)) {
                throw new ClientAlreadyExistException(ErrorMessage.CLIENT_ALREADY_EXIST);
            }
        });
    }

    private Manager searchManagerByLastname(ClientCreateDto clientCreateDto) {
        String lastname = clientCreateDto.getManagerLastName();
        return managerRepository.findManagerByLastName(lastname)
                .orElseThrow(() -> new ManagerNotFoundException(ErrorMessage.MANAGER_NOT_FOUND));
    }
}
