package com.example.bankapp.mapper;

import com.example.bankapp.dto.TransactionAfterCreateDto;
import com.example.bankapp.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "transaction.debitAccountId.name", target = "debitAccountName")
    @Mapping(source = "transaction.debitAccountId.client.firstName", target = "debitClientFirstName")
    @Mapping(source = "transaction.debitAccountId.client.lastName", target = "debitClientLastName")
    @Mapping(source = "transaction.creditAccountId.name", target = "creditAccountName")
    @Mapping(source = "transaction.creditAccountId.client.firstName", target = "creditAccountFirstName")
    @Mapping(source = "transaction.creditAccountId.client.lastName", target = "creditAccountLastName")
    TransactionAfterCreateDto transactionToAfterCreateDto(Transaction transaction);

    List<TransactionAfterCreateDto> toTransactionDtoList(List<Transaction> transactions);
}
