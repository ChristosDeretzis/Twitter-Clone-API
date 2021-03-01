package com.christos.app.twittercloneapi.exceptions.exceptions;

public class UserNotFoundException extends RuntimeException implements NotFoundException{

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
