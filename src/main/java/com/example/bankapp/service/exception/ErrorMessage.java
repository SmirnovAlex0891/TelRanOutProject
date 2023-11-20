package com.example.bankapp.service.exception;

public class ErrorMessage {
    public static final String ACCOUNT_BY_NAME_NOT_FOUND = "Account with Name: %s not found";
    public static final String ACCOUNT_BY_AMOUNT_AND_BY_CLIENT_ID_NOT_FOUND =
            "Account with Transaction amount: %.2f and Client ID: %d not found";
    public static final String ACCOUNT_IS_NOT_ACTIVE = "Account with Name: %s is not active";
    public static final String AGREEMENTS_NOT_FOUND =
            "Agreements with Client Name: %s %s and Manager login: %s not found";
    public static final String CLIENT_NOT_FOUND = "Client with ID: %d not found";
    public static final String CLIENT_NOT_HAVE_ACCOUNT = "Client with login: %s does not have an account: %s";
    public static final String INSUFFICIENT_FUNDS = "There are not enough funds on the balance of account: %s";
    public static final String MANAGER_NOT_FOUND =
            "Manager with AccountBalance greater than or equal to: %.2f not found";
    public static final String PRODUCT_NOT_FOUND = "Product with ID: %d not found";
    public static final String VALIDATION_FAILED = "%s. Validation failed %s: %.5f is incorrect";
}
