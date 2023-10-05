package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Api(description = "Controller for Account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/all")
    @ApiOperation("Get all accounts")
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get an account by id")
    public Account getAccount(@PathVariable("id") Long id) {
        return accountService.getAccountById(id);
    }
}
