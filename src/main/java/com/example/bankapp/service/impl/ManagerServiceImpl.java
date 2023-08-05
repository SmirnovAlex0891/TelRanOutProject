package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.mapper.ManagerMapper;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import com.example.bankapp.service.exception.ErrorMessage;
import com.example.bankapp.service.exception.ManagerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerMapper managerMapper;

    private final ManagerRepository managerRepository;

    @Override
    public ManagerDTO getManagerByName(String name) {

        return managerMapper.toDto(managerRepository.findManagerByLastName(name)
                .orElseThrow(() -> new ManagerNotFoundException(
                        String.format(ErrorMessage.MANAGER_NOT_FOUND, "name", name)
                )));
    }

    @Override
    public ManagerDTO getManagerById(Long id) {
        return managerMapper.toDto(managerRepository.findManagerById(id).get());
    }
}
