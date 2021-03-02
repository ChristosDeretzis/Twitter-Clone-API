package com.christos.app.twittercloneapi.exceptions.exceptions;

public class TweetNotFoundException extends NotFoundException {

    public TweetNotFoundException(String message) {
        super(message);
    }

    public TweetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TweetNotFoundException(Throwable cause) {
        super(cause);
    }
}
