package com.example.bankapp.mapper;

import com.example.bankapp.dto.ManagerAndClientsDto;
import com.example.bankapp.entity.Manager;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerAndClientsDto toDto(Manager manager);
    List<ManagerAndClientsDto> toDtoList(List<Manager> managers);
}
