package com.example.bankapp.dto;

import com.example.bankapp.validation.annotation.PositiveDouble;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class CreateTransactionDto {
    @Schema(description = "Transaction type can be: TRANSFER or PAYMENT", example = "TRANSFER")
    String typeTransaction;
    @Schema(description = "The amount to be transferred cannot be negative", example = "50")
    @PositiveDouble
    Double amount;
    @Schema(description = "Transaction description", example = "50 usd from Sender_client_name to Recipient_client_name")
    String description;
    @Schema(description = "Beneficiary account name", example = "LT60 1010 0123 4567 8901")
    String debitAccountName;
    @Schema(description = "Sender's account name", example = "BY86 AKBB 1010 0000 0029 6602 0000")
    String creditAccountName;
}
