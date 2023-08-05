package com.example.bankapp.controller;

import com.example.bankapp.dao.ManagerDAO;
import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.entity.Manager;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.ManagerService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

}
