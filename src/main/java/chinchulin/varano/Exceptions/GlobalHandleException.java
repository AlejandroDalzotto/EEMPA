package chinchulin.varano.Exceptions;

import chinchulin.varano.Payloads.ApiOnErrorResponse;
import chinchulin.varano.Payloads.ApiOnInvalidInputDataResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiOnInvalidInputDataResponse response = new ApiOnInvalidInputDataResponse(HttpStatus.BAD_REQUEST.value(), errors, false);

        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(EntityRepeatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiOnErrorResponse handleRepeatedEntityException(EntityRepeatedException ex) {

        return new ApiOnErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                false
        );
    }
}