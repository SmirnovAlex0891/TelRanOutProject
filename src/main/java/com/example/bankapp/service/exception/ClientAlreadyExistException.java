package com.example.bankapp.service.exception;

public class ClientAlreadyExistException extends RuntimeException{
    public ClientAlreadyExistException(String message) {
        super(message);
    }
}
