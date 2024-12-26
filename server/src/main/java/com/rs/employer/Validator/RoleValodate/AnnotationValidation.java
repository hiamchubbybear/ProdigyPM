/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.rs.employer.Validator.RoleValodate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AnnotationValidation implements ConstraintValidator<ValidateRole, Set> {

    @Override
    public boolean isValid(Set ValidateCustomerType, ConstraintValidatorContext context) {
        List<String> ValidateCustomerTypes = Arrays.asList("ADMIN", "USER", "SUPPLIER", "VENDOR");
        return ValidateCustomerTypes.contains(ValidateCustomerType);
    }

}
