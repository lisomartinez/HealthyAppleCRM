package ar.com.healthyapple.crm_web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyExistException extends RuntimeException {

    public static final String DESCRIPTION = "Already Exist";
    private static final long serialVersionUID = 38384632456235000L;


    public AlreadyExistException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
