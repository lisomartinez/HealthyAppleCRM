package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDateFormatException extends RuntimeException {
    public static final String DESCRIPTION = "Invalid date format";
    private static final long serialVersionUID = 68456298234234004L;

    public InvalidDateFormatException() {
        super(DESCRIPTION);
    }

    public InvalidDateFormatException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
