package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends RuntimeException {
    public static final String DESCRIPTION = "Invalid parameter";
    private static final long serialVersionUID = 822265235232334007L;

    public InvalidParameterException() {
        super(DESCRIPTION);
    }

    public InvalidParameterException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
