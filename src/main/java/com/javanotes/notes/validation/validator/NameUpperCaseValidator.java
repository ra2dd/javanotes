package com.javanotes.notes.validation.validator;

import com.javanotes.notes.validation.NameUpperCaseConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

public class NameUpperCaseValidator implements ConstraintValidator<NameUpperCaseConstraint, String>
{

    @Override
    public void initialize(NameUpperCaseConstraint constraintAnnotation)
    {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext)
    {
        return Character.isUpperCase(name.charAt(0));
    }
}
