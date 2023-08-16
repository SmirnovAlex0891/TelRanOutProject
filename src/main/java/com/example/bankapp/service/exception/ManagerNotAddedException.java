package com.example.bankapp.service.exception;

public class ManagerNotAddedException extends RuntimeException{
    public ManagerNotAddedException(String message) {
        super(message);
    }
}
