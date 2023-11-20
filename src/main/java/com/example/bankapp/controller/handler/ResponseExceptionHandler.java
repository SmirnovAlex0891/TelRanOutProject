package com.example.bankapp.controller.handler;

import com.example.bankapp.dto.ErrorExtensionDto;
import com.example.bankapp.service.exception.*;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ClientNotFoundException.class,
            AccountNotFoundException.class,
            ManagerNotFoundException.class,
            ProductNotFoundException.class,
            AgreementsNotFoundException.class,
            UsernameNotFoundException.class,
            AuthenticationException.class})
    public ResponseEntity<ErrorExtensionDto> handleClientNotFoundException(Exception exception) {
        ErrorExtensionDto body = new ErrorExtensionDto(
                NOT_FOUND.toString(),
                exception.getMessage());
        return new ResponseEntity<>(body, NOT_FOUND);
    }

    @ExceptionHandler({AccountIsNotActiveException.class, ClientNotHaveAccountException.class})
    public ResponseEntity<ErrorExtensionDto> handleAccountIsNotActiveException(Exception exception) {
        ErrorExtensionDto body = new ErrorExtensionDto(
                ErrorCode.PROBLEM_WITH_ACCOUNT,
                exception.getMessage());
        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    @ExceptionHandler({InsufficientFundsException.class})
    public ResponseEntity<ErrorExtensionDto> handleInsufficientFundsException(Exception exception) {
        ErrorExtensionDto body = new ErrorExtensionDto(
                ErrorCode.PROBLEM_FUNDS,
                exception.getMessage());
        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Double amount = null;
        String field = null, defaultMessage = null;
        for (FieldError f : fieldErrors) {
            amount = (Double) f.getRejectedValue();
            field = f.getField();
            defaultMessage = f.getDefaultMessage();
        }
        ErrorExtensionDto body = new ErrorExtensionDto(
                BAD_REQUEST.toString(),
                String.format(ErrorMessage.VALIDATION_FAILED, defaultMessage, field, amount));
        return new ResponseEntity<>(body, BAD_REQUEST);
    }
}
