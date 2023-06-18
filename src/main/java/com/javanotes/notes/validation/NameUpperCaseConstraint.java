package com.javanotes.notes.validation;

import com.javanotes.notes.validation.validator.NameUpperCaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameUpperCaseValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameUpperCaseConstraint
{
    String message() default "Name/Last Name should start with uppercase letter.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
