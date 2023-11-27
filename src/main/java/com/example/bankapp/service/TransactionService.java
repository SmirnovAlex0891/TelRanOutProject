package com.example.bankapp.service;

import com.example.bankapp.dto.CreateTransactionDto;
import com.example.bankapp.dto.TransactionDto;

import java.security.Principal;

public interface TransactionService {
    TransactionDto createTransaction(CreateTransactionDto createTransactionDto, Principal principal);
}
