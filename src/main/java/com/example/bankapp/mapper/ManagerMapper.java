package com.example.bankapp.mapper;

import com.example.bankapp.dto.ManagerDto;
import com.example.bankapp.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDto toDto(Manager manager);
}
