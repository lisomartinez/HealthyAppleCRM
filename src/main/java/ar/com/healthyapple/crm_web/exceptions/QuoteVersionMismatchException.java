package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuoteVersionMismatchException extends RuntimeException {
    public static final String DESCRIPTION = "Quote version mismatch";
    private static final long serialVersionUID = 987283452897463012L;

    public QuoteVersionMismatchException() {
        super(DESCRIPTION);
    }

    public QuoteVersionMismatchException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
