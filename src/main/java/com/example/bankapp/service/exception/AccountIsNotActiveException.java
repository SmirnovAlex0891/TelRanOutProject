package com.example.bankapp.service.exception;

public class AccountIsNotActiveException extends RuntimeException{
    public AccountIsNotActiveException(String message) {
        super(message);
    }
}
