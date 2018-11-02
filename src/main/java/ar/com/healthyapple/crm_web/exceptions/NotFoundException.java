package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends  RuntimeException {
    public static final String DESCRIPTION = "Not Found Exception";
    private static final long serialVersionUID = 6830756676887746008L;

    public NotFoundException() {
        super(DESCRIPTION);
    }

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}

