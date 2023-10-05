package com.example.bankapp.mapper;

import com.example.bankapp.dto.ClientCreateDto;
import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import javax.persistence.SecondaryTable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface ClientMapper {
    @Mapping(source = "client.manager.lastName", target = "managerLastName")
    @Mapping(source = "accounts", target = "accounts", qualifiedByName = "accounts")
    ClientDto clientToClientDto(Client client);
    List<ClientDto> toDtoList(List<Client> clientList);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    Client createClientEntity(ClientCreateDto clientCreateDto);

    @Named("accounts")
    default Set<String> accountNames(Set<Account> accounts) {
        return accounts.stream()
                .map(Account::getName)
                .collect(Collectors.toSet());
    }
}
