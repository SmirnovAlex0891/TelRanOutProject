package com.example.bankapp.service.impl;

import com.example.bankapp.entity.Account;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
