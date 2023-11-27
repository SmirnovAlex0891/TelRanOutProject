package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Api(value = "Controller for clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an client by id", nickname = "getClientById", response = ClientDto.class)
    public ClientDto getClientById(@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete an client by id")
    public ResponseEntity<Void> deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.ok(null);
    }
}
