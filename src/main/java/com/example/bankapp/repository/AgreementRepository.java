package com.example.bankapp.repository;

import com.example.bankapp.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    String s = "SELECT ag FROM Agreement ag " +
            "JOIN ag.account ac JOIN ac.client cl " +
            "JOIN cl.manager m " +
            "WHERE cl.firstName = :firstName AND cl.lastName = :lastName AND m.email = :email";
    @Query(value = s)
    Optional<Collection<Agreement>> findAgreementByClientFirstNameLastNameAndManagerEmail(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email);
}
