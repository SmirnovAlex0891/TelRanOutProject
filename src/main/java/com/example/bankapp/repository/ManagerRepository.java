package com.example.bankapp.repository;

import com.example.bankapp.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    String s = "SELECT m FROM Manager m JOIN m.clients c JOIN c.accounts ac WHERE ac.balance >= :balance";
    @Query(value = s)
    Optional<List<Manager>> findManagerByBalance(@Param("balance") Double balance);
    Optional<Manager> findManagerByEmail(String email);
}
