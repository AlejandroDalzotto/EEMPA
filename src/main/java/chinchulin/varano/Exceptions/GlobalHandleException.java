package chinchulin.varano.Exceptions;

import chinchulin.varano.Payloads.ApiOnErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalHandleException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiOnErrorResponse handleNoResourceFoundException(EntityNotFoundException ex) {

        return new ApiOnErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                false
        );
    }
}