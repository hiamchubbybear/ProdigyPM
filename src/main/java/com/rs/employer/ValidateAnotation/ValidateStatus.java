package com.rs.employer.ValidateAnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StatusAnotation.class)

public @interface ValidateStatus {

	public String message() default "Invalid Role: It must be one in {\\\"Active\\\", \\\"Inactive\\\", \\\"Suspend\\\", \\\"Offline\\\"} ";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
