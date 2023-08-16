package com.example.bankapp.service.impl;

import com.example.bankapp.dto.CreateTransactionDto;
import com.example.bankapp.dto.TransactionAfterCreateDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.mapper.TransactionMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.TransactionService;
import com.example.bankapp.service.exception.AccountNotFoundException;
import com.example.bankapp.service.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;


    @Override
    public TransactionAfterCreateDto createTransaction(CreateTransactionDto dto) {
        Account debitAccount = checkDebitAccountName(dto.getDebitAccountName());
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setDescription(dto.getDescription());
        transaction.setType(dto.getType());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setDebitAccountId(debitAccount);
        transaction.setCreditAccountId(accountRepository.findAccountByName(dto.getCreditAccountName()).get());
        return transactionMapper.transactionToAfterCreateDto(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionAfterCreateDto> getAllTransactions() {

        return transactionMapper.toTransactionDtoList(transactionRepository.findAll());
    }

    private Account checkDebitAccountName(String debitAccountName) {
        return accountRepository.findAccountByName(debitAccountName).orElseThrow(() -> new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));
    }
}
