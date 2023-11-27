package com.example.bankapp.service.impl;

import com.example.bankapp.dto.CreateTransactionDto;
import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.entity.enums.AccountStatus;
import com.example.bankapp.entity.enums.CurrencyType;
import com.example.bankapp.mapper.TransactionMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.TransactionService;
import com.example.bankapp.service.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public TransactionDto createTransaction(CreateTransactionDto dto, Principal principal) {
        Account debitAccount = checkAccountName(dto.getDebitAccountName());
        Account creditAccount = checkCreditAccountNameByEmail(dto.getCreditAccountName(), principal.getName());
        Transaction transaction = transactionMapper.toTransaction(dto);
        if (creditAccount.getBalance() >= dto.getAmount()) {
            creditAccount.setBalance((creditAccount.getBalance() - dto.getAmount()));
            accountRepository.save(creditAccount);
            double coefficient = currencyConversionFactor(creditAccount.getCurrencyType(), debitAccount.getCurrencyType());
            debitAccount.setBalance((debitAccount.getBalance() + dto.getAmount() * coefficient));
            accountRepository.save(debitAccount);
            transaction.setCreditAccount(creditAccount);
            transaction.setDebitAccount(debitAccount);
            transactionRepository.save(transaction);
            return transactionMapper.transactionToDto(transactionRepository.save(transaction));
        }
        throw new InsufficientFundsException(String.format(
                ErrorMessage.INSUFFICIENT_FUNDS, creditAccount.getName()));

    }
    private Account checkAccountName(String accountName) {
        Account account = accountRepository.findAccountByName(accountName)
                .orElseThrow(() -> new AccountNotFoundException(String.format(
                        ErrorMessage.ACCOUNT_BY_NAME_NOT_FOUND, accountName)));
        if (account.getStatus() != AccountStatus.IN_USE) {
            throw new AccountIsNotActiveException(String.format(
                    ErrorMessage.ACCOUNT_IS_NOT_ACTIVE, accountName));
        }
        return account;
    }
    private Account checkCreditAccountNameByEmail(String accountName, String email) {
        Client client = clientRepository.findClientByEmail(email).get();
        if (client.getAccounts().stream()
                .map(Account::getName)
                .anyMatch(n -> n.equals(accountName))) {
            return checkAccountName(accountName);
        }
        throw new ClientNotHaveAccountException(String.format(ErrorMessage.CLIENT_NOT_HAVE_ACCOUNT, email, accountName));
    }
    private double currencyConversionFactor(CurrencyType first, CurrencyType second) {
        double coefficient = 1;
        switch (first.getValue() + "-" + second.getValue()) {
            case "USD-EUR":
                coefficient = 0.91;
            break;
            case "USD-PLN":
                coefficient = 3.98;
            break;
            case "EUR-USD":
                coefficient = 1.09;
            break;
            case "EUR-PLN":
                coefficient = 4.35;
            break;
            case "PLN-USD":
                coefficient = 0.25;
            break;
            case "PLN-EUR":
                coefficient = 0.23;
            break;
        }
        return coefficient;
    }
}
