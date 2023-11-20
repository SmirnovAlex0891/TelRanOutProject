package com.example.bankapp.entity.enums;

public enum TransactionType {
    TRANSFER("TRANSFER"),
    PAYMENT("PAYMENT");
    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
