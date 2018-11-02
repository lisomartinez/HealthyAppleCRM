package ar.com.healthyapple.crm_web.service.Client;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class ClientInfoValidatorImpl implements ClientInfoValidator {

    @Override
    public boolean isMobileValid(String mobile) {
        try {
            Long.parseLong(mobile);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isFirstNameValid(String firstName) {
        return firstName != null && !firstName.isEmpty() && firstName.trim().length() != 0;

    }

    @Override
    public boolean isLastNameValid(String lastName) {
        return lastName != null && !lastName.isEmpty() && lastName.trim().length() != 0;
    }

    @Override
    public boolean isEmailValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    @Override
    public boolean isAddressValid(String address) {
        return address != null && !address.isEmpty() && address.trim().length() != 0;
    }

    @Override
    public boolean isStartDateValid(String startDate) {
        LogManager.getLogger().debug(startDate);
        try {
//            DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-DD");
            LocalDate localDate = LocalDate.parse(startDate);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }

    }
}
