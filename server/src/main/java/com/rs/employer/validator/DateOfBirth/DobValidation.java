package com.rs.employer.validator.DateOfBirth;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DobValidation implements ConstraintValidator<DobValidator, LocalDate> {
    private int min;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (Objects.isNull(value))
            return true;
        else {
            long year = ChronoUnit.YEARS.between(value, LocalDate.now());
            return year >= min;
        }
    }

}
