package com.example.bankapp.entity.enums;

public enum AccountStatus {
    IN_USE("IN_USE"),
    SUSPENDED("SUSPENDED"),
    REMOVED("REMOVED");
    private final String value;
    AccountStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
