package com.example.bankapp.controller;

import com.example.bankapp.service.ManagerService;
import com.example.bankapp.service.exception.ManagerNotAddedException;
import com.example.bankapp.service.exception.ManagerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllManagers() {
        return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public String addManager(@RequestParam String firstname, @RequestParam String lastname, Map<String, Object> model) {
        try {
            managerService.addManager(lastname, firstname);
            model.put("add", "Manager " + lastname + " " + firstname + " added successfully");
        } catch (ManagerNotAddedException e) {
            model.put("errors_add", e.getMessage());
        }

        return "managers";
    }

    @PostMapping(path = "/filter")
    public String getManagerByName(@RequestParam String lastname, Map<String, Object> model) {
        if (lastname != null && !lastname.isEmpty()) {
            try {
                model.put("managers", managerService.getManagerByName(lastname));
            } catch (ManagerNotFoundException e) {
                model.put("errors_filter", e.getMessage());
            }

        } else {
            model.put("managers", managerService.getAllManagers());
        }
        return "managers";
    }

    @PostMapping("/delete")
    public String deleteManager(@RequestParam String firstname, @RequestParam String lastname, Map<String, Object> model) {

        try {
            managerService.deleteManager(lastname, firstname);
        } catch (ManagerNotFoundException e) {
            model.put("errors_delete", e.getMessage());
        }

        return "managers";
    }
}
