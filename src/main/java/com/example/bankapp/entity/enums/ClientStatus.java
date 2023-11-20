package com.example.bankapp.entity.enums;

public enum ClientStatus {
    IN_USE("IN_USE"),
    BLOCKED("BLOCKED");
    private final String value;

    ClientStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
