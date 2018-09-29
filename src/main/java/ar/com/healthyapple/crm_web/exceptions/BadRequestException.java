package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
    public static final String DESCRIPTION = "Bad Request";
    private static final long serialVersionUID = 6830756676887746370L;

    public BadRequestException() {
        super(DESCRIPTION);
    }

    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}