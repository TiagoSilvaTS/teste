package com.testing.demo.exception;


import com.testing.demo.exception.authExeptions.WrongCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionClass  {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)

    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex, WebRequest req){
        // Build your custom response object and access the exception message using ex.getMessage()
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        AppError error = AppError.builder()
                .message(errors)
                .exception(ex.getClass().getSimpleName())
                .path(((ServletWebRequest)req).getRequest().getRequestURI().toString())
                .httpMethod(((ServletWebRequest)req).getRequest().getMethod().toString())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(
            value = {
                    WrongCredentialsException.class
            }
    )
    public ResponseEntity<AppError> handleWrongCredentialsException(Exception ex, HttpServletRequest req) {
        List<String> errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        var error = AppError.builder()
                .message(errors)
                .exception(ex.getClass().getSimpleName())
                .path(req.getRequestURI())
                .httpMethod(req.getMethod())
                .build();

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

}
