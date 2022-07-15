package com.testing.demo.exception.NotFound;

import com.testing.demo.exception.AppException;

public class UserNotFoundException extends AppException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
