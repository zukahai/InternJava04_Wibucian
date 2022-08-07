package com.java04.wibucian.validations.validator;

import com.java04.wibucian.validations.annotation.ValidDate;
import com.java04.wibucian.validations.annotation.ValidISO8061Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.bind.DatatypeConverter;

public class ISO8061DateValidator implements ConstraintValidator<ValidISO8061Date, String> {

    @Override
    public void initialize(ValidISO8061Date validISO8061Date) {
    }

    @Override
    public boolean isValid(String dateInput, ConstraintValidatorContext cxt) {
        try {
            DatatypeConverter.parseDateTime(dateInput);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}