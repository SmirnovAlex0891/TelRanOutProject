package com.example.bankapp.repository;

import com.example.bankapp.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByName(String name);
//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "agreement")
//    List<Account> findAll();
    Account findAccountById(Long id);
}
