package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.mapper.AccountMapper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.service.exception.AccountNotFoundException;
import com.example.bankapp.service.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Override
    public AccountDto getAccount(Double amount, Long clientId) {
        return accountMapper.accountToAccountDto(
                accountRepository.findAccountByTransactionAndClient(amount, clientId)
                        .orElseThrow(() -> new AccountNotFoundException(String.format(
                                ErrorMessage.ACCOUNT_BY_AMOUNT_AND_BY_CLIENT_ID_NOT_FOUND, amount, clientId
                        ))));
    }
}
