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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public TransactionAfterCreateDto createTransaction(CreateTransactionDto dto) {
        Account debitAccount = checkAccountName(dto.getDebitAccountName());
        Account creditAccount = checkAccountName(dto.getCreditAccountName());
        //Double interestRate = creditAccount.getAgreement().getInterestRate();
        //Double sumForCreditAccount = dto.getAmount() + (dto.getAmount() * interestRate);

        creditAccount.setBalance((creditAccount.getBalance() - dto.getAmount()));
        accountRepository.save(creditAccount);
        debitAccount.setBalance((debitAccount.getBalance() + dto.getAmount()));
        accountRepository.save(debitAccount);

        Transaction transactionDebitAccount = new Transaction();
        transactionDebitAccount.setAmount(dto.getAmount());
        transactionDebitAccount.setDescription(dto.getDescription());
        transactionDebitAccount.setType(dto.getType());
        transactionDebitAccount.setCreatedAt(LocalDateTime.now());
        transactionDebitAccount.setDebitAccountId(debitAccount);
        transactionDebitAccount.setCreditAccountId(creditAccount);

        Transaction transactionCreditAccount = new Transaction();
        transactionCreditAccount.setAmount(dto.getAmount());
        transactionCreditAccount.setDescription(dto.getDescription());
        transactionCreditAccount.setType("receiving");
        transactionCreditAccount.setCreatedAt(LocalDateTime.now());
        transactionCreditAccount.setDebitAccountId(debitAccount);
        transactionCreditAccount.setCreditAccountId(creditAccount);
        transactionRepository.save(transactionCreditAccount);
        return transactionMapper.transactionToAfterCreateDto(transactionRepository.save(transactionDebitAccount));
    }

    @Override
    public List<TransactionAfterCreateDto> getAllTransactions() {

        return transactionMapper.toTransactionDtoList(transactionRepository.findAll());
    }

    private Account checkAccountName(String debitAccountName) {
        return accountRepository.findAccountByName(debitAccountName).orElseThrow(() -> new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND));
    }
}
