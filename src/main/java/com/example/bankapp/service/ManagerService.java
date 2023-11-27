package com.example.bankapp.service;

import com.example.bankapp.dto.ManagerDto;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> getManagers(Double sum);
}
