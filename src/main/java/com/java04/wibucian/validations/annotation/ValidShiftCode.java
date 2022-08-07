package com.java04.wibucian.validations.annotation;

import com.java04.wibucian.validations.validator.ShiftCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {ShiftCodeValidator.class}
)
public @interface ValidShiftCode {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
