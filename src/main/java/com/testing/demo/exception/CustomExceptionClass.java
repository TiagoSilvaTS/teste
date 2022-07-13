package com.testing.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionClass  {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)

    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, WebRequest req){
        // Build your custom response object and access the exception message using ex.getMessage()
        AppError error = AppError.builder()
                .message(ex.getMessage())
                .exception(ex.getClass().getSimpleName())
                .path(((ServletWebRequest)req).getRequest().getRequestURI().toString())
                .httpMethod(((ServletWebRequest)req).getRequest().getMethod().toString())
                .build();

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
