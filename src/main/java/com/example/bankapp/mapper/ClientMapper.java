package com.example.bankapp.mapper;

import com.example.bankapp.dto.ClientCreateDto;
import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface ClientMapper {
    @Mapping(source = "client.manager.lastName", target = "managerLastName")
    ClientDto clientToClientDto(Client client);
    List<ClientDto> toDtoList(List<Client> clientList);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    Client createClientEntity(ClientCreateDto clientCreateDto);

}
