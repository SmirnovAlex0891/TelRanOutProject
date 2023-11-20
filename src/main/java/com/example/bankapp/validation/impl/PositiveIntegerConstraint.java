package com.example.bankapp.validation.impl;

import com.example.bankapp.validation.annotation.PositiveDouble;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class PositiveIntegerConstraint implements ConstraintValidator<PositiveDouble, Double> {
    private static final String POSITIVE_DECIMAL_PATTERN = "\\d{0,5}(\\.\\d{0,2})";
    @Override
    public void initialize(PositiveDouble constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .filter(s -> !s.toString().isBlank())
                .map(s -> Double.parseDouble(s.toString()))
                .map(s -> s.toString().matches(POSITIVE_DECIMAL_PATTERN))
                .orElse(false);
    }
}
