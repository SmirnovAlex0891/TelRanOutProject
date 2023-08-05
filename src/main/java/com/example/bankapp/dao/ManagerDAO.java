package com.example.bankapp.dao;

import com.example.bankapp.dto.ManagerDTO;
import com.example.bankapp.entity.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ManagerDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Manager> index() {
        return jdbcTemplate.query("SELECT * FROM managers", new BeanPropertyRowMapper<>(Manager.class));
    }

    public ManagerDTO getManagerByName(String name) {
        System.out.println(name);
        Manager manager = jdbcTemplate.query("SELECT * FROM managers WHERE last_name=?", new Object[]{name}, new BeanPropertyRowMapper<>(Manager.class))
                .stream().findAny().orElse(null);
        if (manager == null) {
            System.out.println("!!!!!!!!!!!!!!!!!!!null");
        }
        ManagerDTO managerDTO = new ManagerDTO(manager.getFirstName(), manager.getLastName());
        if (manager == null) {
            System.out.println("!!!!!!!!!!!!!!!!!!!null");
        }

        return managerDTO;
    }
}
