package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidClientNameException extends RuntimeException {
    public static final String DESCRIPTION = "Invalid type format";
    private static final long serialVersionUID = 11116298234234003L;

    public InvalidClientNameException() {
        super(DESCRIPTION);
    }

    public InvalidClientNameException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
