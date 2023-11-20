package com.example.bankapp.dto;

import lombok.Value;

@Value
public class ProductDto {
    String name;
    String status;
    String currencyType;
    String interestRate;
    String limit;
    String createdAt;
    String updatedAt;
}
