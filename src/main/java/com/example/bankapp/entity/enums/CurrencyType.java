package com.example.bankapp.entity.enums;

public enum CurrencyType {
    EUR("EUR"),
    USD("USD"),
    PLN("PLN");
    private final String value;

    CurrencyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
