package com.example.bankapp.service;

import com.example.bankapp.dto.CreateTransactionDto;
import com.example.bankapp.dto.TransactionAfterCreateDto;
import com.example.bankapp.entity.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionAfterCreateDto createTransaction(CreateTransactionDto createTransactionDto);
    List<TransactionAfterCreateDto> getAllTransactions();
}
