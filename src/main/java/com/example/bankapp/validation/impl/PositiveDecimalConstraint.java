package com.example.bankapp.validation.impl;

import com.example.bankapp.validation.annotation.PositiveDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Optional;

public class PositiveDecimalConstraint implements ConstraintValidator<PositiveDecimal, BigDecimal> {

    private static final String POSITIVE_DECIMAL_PATTERN = "\\d{0,15}(\\.\\d{0,4})?";
    @Override
    public void initialize(PositiveDecimal constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .filter(s -> !s.toString().isBlank())
                .map(s -> s.toString().matches(POSITIVE_DECIMAL_PATTERN))
                .orElse(false);
    }
}
