package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidMobileNumberFormatException extends RuntimeException {
    public static final String DESCRIPTION = "Invalid Mobile number format";
    private static final long serialVersionUID = 686667766623341006L;

    public InvalidMobileNumberFormatException() {
        super(DESCRIPTION);
    }

    public InvalidMobileNumberFormatException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
