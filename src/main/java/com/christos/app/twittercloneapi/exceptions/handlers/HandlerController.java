package com.christos.app.twittercloneapi.exceptions.handlers;

import com.christos.app.twittercloneapi.exceptions.exceptions.ErrorResponse;
import com.christos.app.twittercloneapi.exceptions.exceptions.TweetNotFoundException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserAlreadyExistsException;
import com.christos.app.twittercloneapi.exceptions.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerController {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleTweetNotFoundException(TweetNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
