package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ManagerDto;
import com.example.bankapp.entity.Manager;
import com.example.bankapp.mapper.ManagerMapper;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import com.example.bankapp.service.exception.ErrorMessage;
import com.example.bankapp.service.exception.ManagerNotAddedException;
import com.example.bankapp.service.exception.ManagerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerMapper managerMapper;
    private final ManagerRepository managerRepository;

    @Override
    public List<ManagerDto> getAllManagers() {
        List<Manager> managers = managerRepository.findAll();
        List<ManagerDto> managerDTOS = new ArrayList<>();
        if (!managers.isEmpty()) {
            for (Manager m : managers) {
                managerDTOS.add(managerMapper.toDto(m));
            }
        }
        return managerDTOS;
    }

    @Override
    public ManagerDto getManagerByName(String name) {

        return managerMapper.toDto(managerRepository.findManagerByLastName(name)
                .orElseThrow(() -> new ManagerNotFoundException(
                        String.format(ErrorMessage.MANAGER_NOT_FOUND, "name", name)
                )));
    }

    @Override
    public void addManager(String lastname, String firstname) {

        if (!managerRepository.findManagerByLastNameAndFirstName(lastname, firstname).isPresent()) {
            Manager manager = new Manager();
            manager.setFirstName(firstname);
            manager.setLastName(lastname);
            manager.setStatus("ACTIVE");
            manager.setCreatedAt(LocalDateTime.now());
            manager.setUpdatedAt(LocalDateTime.now());
            managerRepository.save(manager);
        }else {
            throw new ManagerNotAddedException(String.format(ErrorMessage.MANAGER_ALREADY_EXISTS, "name", lastname, firstname));
        }
    }

    @Override
    public void deleteManager(String lastname, String firstname) {
        Manager manager = managerRepository.findManagerByLastNameAndFirstName(lastname, firstname).orElse(null);
        if (manager != null) {
            managerRepository.delete(manager);
        } else {
            throw new ManagerNotFoundException(
                    String.format(ErrorMessage.MANAGER_NOT_FOUND, "name", lastname, firstname));
        }
    }

}
