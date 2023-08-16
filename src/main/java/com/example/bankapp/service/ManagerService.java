package com.example.bankapp.service;

import com.example.bankapp.dto.ManagerDto;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> getAllManagers();

    ManagerDto getManagerByName(String name);

    void addManager(String lastname, String firstname);
    void deleteManager(String lastname, String firstname);
}
