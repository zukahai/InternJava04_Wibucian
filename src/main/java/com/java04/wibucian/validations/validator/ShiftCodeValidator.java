package com.java04.wibucian.validations.validator;

import com.java04.wibucian.validations.annotation.ValidShiftCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ShiftCodeValidator implements ConstraintValidator<ValidShiftCode, Integer> {

    @Override
    public void initialize(ValidShiftCode validShiftCode) {
    }

    @Override
    public boolean isValid(Integer shiftCode, ConstraintValidatorContext cxt) {
        return shiftCode >= 1 && shiftCode <= 3;
    }

}