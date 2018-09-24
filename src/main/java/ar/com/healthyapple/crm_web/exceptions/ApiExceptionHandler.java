package ar.com.healthyapple.crm_web.exceptions;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler( value = { NotFoundException.class     })
    public ErrorMessage notFoundRequest(HttpServletRequest request,  Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());


    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class
    })
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler({
//            Exception.class
//
//    })
//    public ErrorMessage exception(Exception exception) {
//        exception.printStackTrace();
//        return new ErrorMessage("Server Error. " + exception.getMessage());
//    }

}
