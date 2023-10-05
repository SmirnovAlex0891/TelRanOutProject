package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientCreateDto;
import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.entity.Client;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Api(description = "Controller for clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get list of clients")
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create new client")
    public ClientDto createNewClient(@RequestBody ClientCreateDto clientCreateDto) {
        return clientService.addNewClient(clientCreateDto);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get an client by id")
    public ClientDto getClientById(@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("Delete an client by id")
    public void deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
    }
}
