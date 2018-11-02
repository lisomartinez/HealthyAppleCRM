package ar.com.healthyapple.crm_web.service.Client;

import org.springframework.stereotype.Service;

@Service
public interface ClientInfoValidator {
    boolean isMobileValid(String mobile);

    boolean isFirstNameValid(String name);

    boolean isLastNameValid(String lastName);

    boolean isEmailValid(String email);

    boolean isAddressValid(String address);

    boolean isStartDateValid(String startDate);
}
