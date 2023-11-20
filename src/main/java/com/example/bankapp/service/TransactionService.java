package com.example.bankapp.service;

import com.example.bankapp.dto.CreateTransactionDto;
import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Transaction;

import java.security.Principal;
import java.util.Optional;

public interface TransactionService {
    TransactionDto createTransaction(CreateTransactionDto createTransactionDto, Principal principal);
}
