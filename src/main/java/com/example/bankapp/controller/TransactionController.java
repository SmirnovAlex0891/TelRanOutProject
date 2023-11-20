package com.example.bankapp.controller;

import com.example.bankapp.dto.CreateTransactionDto;
import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Validated
@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new Transaction", nickname = "createTransaction", response = TransactionDto.class)
    public TransactionDto createTransaction(@Valid @RequestBody CreateTransactionDto createTransactionDto, Principal principal) {
        return transactionService.createTransaction(createTransactionDto, principal);
    }
}
