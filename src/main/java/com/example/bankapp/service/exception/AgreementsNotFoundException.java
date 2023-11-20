package com.example.bankapp.service.exception;

public class AgreementsNotFoundException extends RuntimeException{
    public AgreementsNotFoundException(String message) {
        super(message);
    }
}
