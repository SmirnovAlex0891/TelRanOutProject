package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto getAccount(Double amount, Long clientId);
}
