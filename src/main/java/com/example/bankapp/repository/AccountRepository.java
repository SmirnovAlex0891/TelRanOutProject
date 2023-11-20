package com.example.bankapp.repository;

import com.example.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByName(String name);
    @Query(value = "SELECT a FROM Account a JOIN a.transactionCredit t WHERE a.client.id = :clientId AND t.amount = :amount")
    Optional<Account> findAccountByTransactionAndClient(@Param("amount") Double amount, @Param("clientId") Long clientId);
}
