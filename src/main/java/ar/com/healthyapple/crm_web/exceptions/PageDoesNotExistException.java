package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PageDoesNotExistException extends RuntimeException{

    public static final String DESCRIPTION = "Page does not Exists Exception";
    private static final long serialVersionUID = 698492387746370L;

    public PageDoesNotExistException() {
        super(DESCRIPTION);
    }

    public PageDoesNotExistException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
