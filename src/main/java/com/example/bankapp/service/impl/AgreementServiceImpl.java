package com.example.bankapp.service.impl;

import com.example.bankapp.dto.AgreementDto;
import com.example.bankapp.dto.ClientFirstNameLastNameDto;
import com.example.bankapp.entity.Agreement;
import com.example.bankapp.mapper.AgreementMapper;
import com.example.bankapp.repository.AgreementRepository;
import com.example.bankapp.service.AgreementService;
import com.example.bankapp.service.exception.AgreementsNotFoundException;
import com.example.bankapp.service.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    @Override
    public Collection<AgreementDto> getAgreements(ClientFirstNameLastNameDto nameDto, Principal principal) {
        String firstName = nameDto.getFirstName();
        String lastName = nameDto.getLastName();
        String email = principal.getName();
        Optional<Collection<Agreement>> optAgreements = agreementRepository.findAgreementByClientFirstNameLastNameAndManagerEmail(firstName, lastName, email);
        if (optAgreements.isPresent()) {
            return agreementMapper.listAgreementToListDto(optAgreements.get());
        } throw new AgreementsNotFoundException(String.format(
                ErrorMessage.AGREEMENTS_NOT_FOUND, firstName, lastName, email));
    }
}
