package com.example.bankapp.controller;

import com.example.bankapp.dao.ManagerDAO;
import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.entity.Manager;
import com.example.bankapp.entity.User;
import com.example.bankapp.entity.enums.Role;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerRepository managerRepository;
    private final ManagerDAO managerDAO;

    private final ManagerService managerService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Manager> findAllManagers() {
        return managerRepository.findAll();
    }
    @GetMapping(path = "/allmanagers")
    public String showAllManagers(Map<String, Object> model) {
        Iterable<Manager> managers = managerDAO.index();
        model.put("m", managers);
        return "managers";
    }

    @GetMapping("/name/{name}")
    public @ResponseBody ManagerDTO getManagerByName(@PathVariable("name") String name) {

        return managerService.getManagerByName(name);
    }

    @GetMapping("/id/{id}")
    public @ResponseBody ManagerDTO getManagerById(@PathVariable("id") Long id) {

        return managerService.getManagerById(id);
    }

    @PostMapping("/add")
    public String addUser(Manager manager, Map<String, Object> model) {
        manager.setStatus(1);
        managerRepository.save(manager);

        return "redirect:/login";
    }

}
