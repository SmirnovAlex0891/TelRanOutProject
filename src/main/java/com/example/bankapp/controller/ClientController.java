package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientCreateDto;
import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.entity.Client;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto createNewClient(@RequestBody ClientCreateDto clientCreateDto) {
        return clientService.addNewClient(clientCreateDto);
    }
}
