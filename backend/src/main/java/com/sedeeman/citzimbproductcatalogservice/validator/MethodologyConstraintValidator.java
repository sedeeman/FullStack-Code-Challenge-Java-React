package com.sedeeman.citzimbproductcatalogservice.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MethodologyConstraintValidator implements ConstraintValidator<MethodologyConstraint, String> {

    @Override
    public void initialize(MethodologyConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.equalsIgnoreCase("Agile") || value.equalsIgnoreCase("Waterfall"));
    }
}
