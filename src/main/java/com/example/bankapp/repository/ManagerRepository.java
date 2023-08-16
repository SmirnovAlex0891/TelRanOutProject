package com.example.bankapp.repository;

import com.example.bankapp.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {


    @Query("SELECT a FROM Manager a WHERE a.lastName=:lastname")
    Optional<Manager> findManagerByLastName(@Param("lastname") String lastName);

    Optional<Manager> findManagerById(Long id);

    Optional<Manager> findManagerByLastNameAndFirstName(String lastname, String firstname);

}
