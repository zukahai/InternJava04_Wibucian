package com.java04.wibucian.validations.annotation;

import com.java04.wibucian.validations.validator.DateValidator;
import com.java04.wibucian.validations.validator.ISO8061DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {ISO8061DateValidator.class}
)
public @interface ValidISO8061Date {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
