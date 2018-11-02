package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEmailAddressException extends RuntimeException {
    public static final String DESCRIPTION = "Invalid Email Address";
    private static final long serialVersionUID = 68456298234234005L;

    public InvalidEmailAddressException() {
        super(DESCRIPTION);
    }

    public InvalidEmailAddressException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
