package com.christos.app.twittercloneapi.exceptions.exceptions;

public class TwitterException extends RuntimeException {
    public TwitterException(String message) {
        super(message);
    }

    public TwitterException(String message, Throwable cause) {
        super(message, cause);
    }
}
