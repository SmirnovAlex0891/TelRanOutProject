package com.example.bankapp.mapper;

import com.example.bankapp.dto.CreateTransactionDto;
import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.entity.enums.TransactionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface TransactionMapper {
    @Mapping(source = "transaction.debitAccount.name", target = "debitAccountName")
    @Mapping(source = "transaction.debitAccount.client.firstName", target = "debitClientFirstName")
    @Mapping(source = "transaction.debitAccount.client.lastName", target = "debitClientLastName")
    @Mapping(source = "transaction.creditAccount.name", target = "creditAccountName")
    @Mapping(source = "transaction.creditAccount.client.firstName", target = "creditAccountFirstName")
    @Mapping(source = "transaction.creditAccount.client.lastName", target = "creditAccountLastName")
    TransactionDto transactionToDto(Transaction transaction);

    @Mapping(target = "createdAt", expression = "java(LocalDate.now())")
    @Mapping(source = "typeTransaction", target = "type", qualifiedByName = "mappingTransactionType")
    Transaction toTransaction(CreateTransactionDto dto);

    @Named("mappingTransactionType")
    default TransactionType mappingTransactionType(String type) {
        return TransactionType.valueOf(type);
    }
}
