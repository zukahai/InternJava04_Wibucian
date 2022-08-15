package com.java04.wibucian.validations.validator;

import com.java04.wibucian.commons.Constant;
import com.java04.wibucian.validations.annotation.ValidDate;
import com.java04.wibucian.validations.annotation.ValidShiftCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

    @Override
    public void initialize(ValidDate validShiftCode) {
    }

    @Override
    public boolean isValid(String dateInput, ConstraintValidatorContext cxt) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constant.DD_MM_YYYY_FORMAT);
            sdf.setLenient(true);
            sdf.parse(dateInput);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}