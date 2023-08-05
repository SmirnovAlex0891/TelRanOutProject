package com.example.bankapp.service;

import com.example.bankapp.dto.ManagerDTO;

public interface ManagerService {
    ManagerDTO getManagerByName(String name);

    ManagerDTO getManagerById(Long id);
}
