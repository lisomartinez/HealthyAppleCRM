package ar.com.healthyapple.crm_web.dto.validations;

import org.apache.commons.validator.routines.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmailAddressValidator implements ConstraintValidator<ValidEmailAddress, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return EmailValidator.getInstance().isValid(email);
    }

    @Override
    public void initialize(ValidEmailAddress constraintAnnotation) {

    }
}
