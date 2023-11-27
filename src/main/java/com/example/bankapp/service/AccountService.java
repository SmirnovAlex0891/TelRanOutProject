package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDto;

public interface AccountService {
    AccountDto getAccount(Double amount, Long clientId);
}
