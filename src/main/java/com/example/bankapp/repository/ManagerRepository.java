package com.example.bankapp.repository;

import com.example.bankapp.entity.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
}
