package ar.com.healthyapple.crm_web.exceptions;

import javassist.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {
            NotFoundException.class})
    public ErrorMessage notFoundRequest(HttpServletRequest request,  Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());


    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            BadRequestException.class,
            InvalidEmailAddressException.class,
            InvalidClientNameException.class,
            InvalidAddressException.class,
            InvalidDateFormatException.class,
            InvalidMobileNumberFormatException.class,
            InvalidMobileNumberFormatException.class
    })

    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {
            Exception.class
    })
    public ErrorMessage exception(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("Server Error. " + exception.getMessage());
    }

}
