package com.example.bankapp.controller;

import com.example.bankapp.dto.ManagerDto;
import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/all/{balance}")
    public List<ManagerDto> getManagers(@PathVariable("balance") Double balance) {
        return managerService.getManagers(balance);
    }
}
