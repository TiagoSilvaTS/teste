package com.testing.demo.exception.authExeptions;

import com.testing.demo.exception.AppException;

public class WrongCredentialsException extends AppException
{
    public WrongCredentialsException(String message) {
        super(message);
    }
}
