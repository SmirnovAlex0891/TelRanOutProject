package com.example.bankapp.controller;

import com.example.bankapp.dto.AgreementDto;
import com.example.bankapp.dto.ClientFirstNameLastNameDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Agreement;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.service.AgreementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/agreement")
@RequiredArgsConstructor
@Api(value = "Controller for Agreement")
public class AgreementController {
    private final AgreementService agreementService;
    @PostMapping("/getAgreements")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get an account by client firstName and lastName")
    public Collection<AgreementDto> getAgreementById(@RequestBody ClientFirstNameLastNameDto nameDto, Principal principal) {
        return agreementService.getAgreements(nameDto, principal);
    }
}
