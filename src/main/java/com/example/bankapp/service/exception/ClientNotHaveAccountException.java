package com.example.bankapp.service.exception;

public class ClientNotHaveAccountException extends RuntimeException{
    public ClientNotHaveAccountException(String message) {
        super(message);
    }
}
