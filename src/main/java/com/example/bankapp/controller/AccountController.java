package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Api(tags = "Account Controller", description = "Should return the AccountDto.class")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{transactionAmount}/{clientId}")
    @ApiOperation(value = "Get account by transaction amount and client id",
            notes = "Returns a complete list of account information with creation and update dates," +
                    " as well as the owner's last name, agreement ID," +
                    " and lists of account transaction IDs.", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful receipt of account data", response = AccountDto.class),
            @ApiResponse(code = 401, message = "The user trying to obtain data is not authorized in the application"),
            @ApiResponse(code = 403, message = "The user trying to obtain the data does not have the necessary access rights"),
            @ApiResponse(code = 404, message = "No account with matching parameters found")
    })
    public AccountDto getAccount(@PathVariable("transactionAmount") Double amount, @PathVariable("clientId") Long clientId) {
        return accountService.getAccount(amount, clientId);
    }
}
