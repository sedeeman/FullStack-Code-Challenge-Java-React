package com.sedeeman.citzimbproductcatalogservice.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MethodologyConstraintValidator.class)
public @interface MethodologyConstraint {

    String message() default "Methodology must be Agile or Waterfall";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
