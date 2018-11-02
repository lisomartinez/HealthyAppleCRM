package ar.com.healthyapple.crm_web.dto.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEmailAddressValidator.class)
public @interface ValidEmailAddress {

    String message() default "Expected valid Email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
