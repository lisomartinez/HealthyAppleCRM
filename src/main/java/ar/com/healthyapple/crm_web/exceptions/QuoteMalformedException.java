package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuoteMalformedException extends RuntimeException {
    public static final String DESCRIPTION = "Quote malformed";

    private static final long serialVersionUID = 9128209358289401215L;

    public QuoteMalformedException() {
        super(DESCRIPTION);
    }

    public QuoteMalformedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
