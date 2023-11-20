package com.example.bankapp.dto;

import com.example.bankapp.validation.annotation.PositiveDouble;
import lombok.Value;

@Value
public class CreateTransactionDto {
    String typeTransaction;
    @PositiveDouble
    Double amount;
    String description;
    String debitAccountName;
    String creditAccountName;
}
