package com.example.bankapp.mapper;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "agreement.id", target = "agreementId")
    @Mapping(source = "client.lastName", target = "clientLastName")
    @Mapping(source = "transactionCredit", target = "creditTransactions", qualifiedByName = "mappingTransactions")
    @Mapping(source = "transactionDebit", target = "debitTransactions", qualifiedByName = "mappingTransactions")
    AccountDto accountToAccountDto(Account account);

    @Named("mappingTransactions")
    default Set<Long> mappingTransactions(Set<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getId)
                .collect(Collectors.toSet());
    }
}
