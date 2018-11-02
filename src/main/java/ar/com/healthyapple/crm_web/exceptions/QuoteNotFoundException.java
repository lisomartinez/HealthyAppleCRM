package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuoteNotFoundException extends RuntimeException {

    public static final String DESCRIPTION = "Quote Not Found";

    private static final long serialVersionUID = 12093582897463010L;

    public QuoteNotFoundException() {
        super(DESCRIPTION);
    }

    public QuoteNotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
