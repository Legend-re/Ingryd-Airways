package com.flywithingryd.IngrydAirways.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldNameValidator implements ConstraintValidator<FieldName, Object> {

    private String fieldName;

    @Override
    public void initialize(FieldName annotation) {
        this.fieldName = annotation.fieldName();
    }
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // Example validation logic: value must not be null or empty
        if (value == null || value.toString().trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    String.format("The field '%s' is required.", fieldName)
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
}
