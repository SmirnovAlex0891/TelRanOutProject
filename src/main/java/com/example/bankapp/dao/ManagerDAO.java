package com.example.bankapp.dao;

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
}
