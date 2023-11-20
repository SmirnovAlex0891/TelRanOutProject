package com.example.bankapp.entity.enums;

public enum ProductStatus {
    IN_USE("IN_USE"),
    BLOCKED("BLOCKED");
    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
