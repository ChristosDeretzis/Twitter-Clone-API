package com.christos.app.twittercloneapi.exceptions.exceptions;

import com.christos.app.twittercloneapi.models.User;

import java.util.function.Supplier;

public class UserNotFoundException extends NotFoundException{

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

}
