package com.example.bankapp.mapper;

import com.example.bankapp.dto.ClientAndAccountBalanceDto;
import com.example.bankapp.dto.ManagerAndClientsDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import com.example.bankapp.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerAndClientsDto toDto(Manager manager);
    List<ManagerAndClientsDto> toDtoList(List<Manager> managers);
}
