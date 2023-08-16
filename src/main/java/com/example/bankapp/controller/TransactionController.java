package com.example.bankapp.controller;

import com.example.bankapp.dto.CreateTransactionDto;
import com.example.bankapp.dto.TransactionAfterCreateDto;
import com.example.bankapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transaction/all")
    public List<TransactionAfterCreateDto> getAllTransactions() {

        return transactionService.getAllTransactions();
    }

    @PostMapping("/transaction/new")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionAfterCreateDto createTransaction(@RequestBody CreateTransactionDto createTransactionDto) {
        return transactionService.createTransaction(createTransactionDto);

    }

}