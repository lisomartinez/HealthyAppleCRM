package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuoteOperationNotAllowedException extends RuntimeException {
    public static final String DESCRIPTION = "Quote Operation Not Allowed";
    private static final long serialVersionUID = 6832345782897463011L;

    public QuoteOperationNotAllowedException() {
        super(DESCRIPTION);
    }

    public QuoteOperationNotAllowedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
