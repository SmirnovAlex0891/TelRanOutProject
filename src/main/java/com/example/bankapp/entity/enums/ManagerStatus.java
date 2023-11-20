package com.example.bankapp.entity.enums;

public enum ManagerStatus {
    PRODUCT_MANAGER("PRODUCT_MANAGER"),
    CLIENT_MANAGER("CLIENT_MANAGER"),
    ADMIN_BANK_APP("ADMIN_BANK_APP");
    private final String value;

    ManagerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
