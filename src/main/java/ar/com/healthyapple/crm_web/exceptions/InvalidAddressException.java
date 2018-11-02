package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidAddressException extends RuntimeException {
    public static final String DESCRIPTION = "Invalid address format";
    private static final long serialVersionUID = 6845765235232334002L;

    public InvalidAddressException() {
        super(DESCRIPTION);
    }

    public InvalidAddressException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
