package com.example.bankapp.dto;

import com.example.bankapp.validation.annotation.PositiveDecimal;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CreateTransactionDto {
    String type;
    @PositiveDecimal
    Double amount;
    String description;
    String debitAccountName;
    String creditAccountName;
}
