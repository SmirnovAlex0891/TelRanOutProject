package com.example.bankapp.controller;

import com.example.bankapp.dao.ManagerDAO;
import com.example.bankapp.entity.Manager;
import com.example.bankapp.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerRepository managerRepository;
    private final ManagerDAO managerDAO;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Manager> findAllManagers() {
        return managerRepository.findAll();
    }
    @GetMapping(path = "/allmanagers")
    public String showAllManagers(Model model) {
        model.addAttribute("m", managerDAO.index());
        return "managers";
    }
}
