package com.example.bankapp.mapper;

import com.example.bankapp.dto.AgreementDto;
import com.example.bankapp.entity.Agreement;
import com.example.bankapp.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
    Collection<AgreementDto> listAgreementToListDto(Collection<Agreement> agreements);
    @Mapping(source = "agreement.account.name", target = "accountName")
    @Mapping(source = "agreement.account.status", target = "accountStatus")
    @Mapping(source = "agreement.account.balance", target = "accountBalance")
    @Mapping(source = "agreement.account.client", target = "clientName", qualifiedByName = "mappingClientName")
    @Mapping(source = "agreement.product.name", target = "productName")
    @Mapping(source = "agreement.sum", target = "agreementSum")
    @Mapping(source = "agreement.status", target = "agreementStatus")
    AgreementDto agreementToDto(Agreement agreement);
    @Named("mappingClientName")
    default String mappingClientName(Client client) {
        return client.getFirstName() + " " + client.getLastName();
    }
}
