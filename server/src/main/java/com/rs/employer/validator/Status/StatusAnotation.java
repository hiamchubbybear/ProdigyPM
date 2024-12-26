/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.rs.employer.validator.Status;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusAnotation implements ConstraintValidator<ValidateStatus, String> {

    @Override
    public boolean isValid(String ValidateCustomerType, ConstraintValidatorContext context) {
        List<String> ValidateCustomerTypes = Arrays.asList("ONLINE", "SUSPEND", "OFFLINE", "NONE");
        return ValidateCustomerTypes.contains(ValidateCustomerType);
    }

}
