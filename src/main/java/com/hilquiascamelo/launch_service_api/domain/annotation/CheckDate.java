package com.hilquiascamelo.launch_service_api.domain.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.time.LocalDate;


@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CheckDate.CheckDateValidatorForDate.class, CheckDate.CheckDateValidatorForLocalDate.class})
public @interface CheckDate {

    String message() default "invalid date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Define várias anotações {@link CheckDate} no mesmo elemento
     */
    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        CheckDate[] value();
    }

    class CheckDateValidatorForDate implements ConstraintValidator<CheckDate, java.util.Date> {

        @Override
        public void initialize(CheckDate constraintAnnotation) {
        }

        @Override
        public boolean isValid(java.util.Date content, ConstraintValidatorContext constraintValidatorContext) {
            if (content == null || "1970-01-01".equals(content.toString())) {
                return false;
            }
            // Valida se a data não é anterior a 1970
            return !content.before(new java.util.Date(0));
        }
    }

    class CheckDateValidatorForLocalDate implements ConstraintValidator<CheckDate, LocalDate> {

        @Override
        public void initialize(CheckDate constraintAnnotation) {
        }

        @Override
        public boolean isValid(LocalDate content, ConstraintValidatorContext constraintValidatorContext) {
            if (content == null || LocalDate.parse("1970-01-01").equals(content)) {
                return false;
            }
            // Valida se a data não é anterior a 1970
            return !content.isBefore(LocalDate.of(1970, 1, 1));
        }
    }
}
