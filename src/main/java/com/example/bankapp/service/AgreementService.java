package com.example.bankapp.service;

import com.example.bankapp.dto.AgreementDto;
import com.example.bankapp.dto.ClientFirstNameLastNameDto;

import java.security.Principal;
import java.util.Collection;

public interface AgreementService {
    Collection<AgreementDto> getAgreements(ClientFirstNameLastNameDto nameDto, Principal principal);
}
