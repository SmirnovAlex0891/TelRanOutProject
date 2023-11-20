package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ClientAndAccountBalanceDto;
import com.example.bankapp.dto.ManagerAndClientsDto;
import com.example.bankapp.dto.ManagerDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import com.example.bankapp.mapper.ManagerMapper;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import com.example.bankapp.service.exception.ErrorMessage;
import com.example.bankapp.service.exception.ManagerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerMapper managerMapper;
    private final ManagerRepository managerRepository;
    @Override
    public List<ManagerDto> getManagers(Double balance) {
        return mappingManagerDto(managerMapper.toDtoList(
                        managerRepository.findManagerByBalance(balance).orElseThrow(
                                () -> new ManagerNotFoundException(String.format(ErrorMessage.MANAGER_NOT_FOUND, balance)))),
                balance);
    }
    private List<ManagerDto> mappingManagerDto(List<ManagerAndClientsDto> managers, Double balance) {
        Set<ManagerDto> managersDto = new HashSet<>();
        for (ManagerAndClientsDto m : managers) {
            Set<ClientAndAccountBalanceDto> clientAndAccountBalanceDtos = new HashSet<>();
            Set<Client> clients = m.getClients();
            for (Client c : clients) {
                Set<Account> accounts = c.getAccounts();
                for (Account a : accounts) {
                    if (a.getBalance() >= balance) {
                        clientAndAccountBalanceDtos.add(new ClientAndAccountBalanceDto(
                                c.getFirstName(), c.getLastName(), a.getName(), a.getBalance().toString()));
                    }
                }
            }
            ManagerDto managerDto = new ManagerDto(
                    m.getFirstName(), m.getLastName(), m.getStatus(), m.getCreatedAt(), m.getUpdatedAt(), clientAndAccountBalanceDtos);
            managersDto.add(managerDto);
        }
        return new ArrayList<>(managersDto);
    }
}
