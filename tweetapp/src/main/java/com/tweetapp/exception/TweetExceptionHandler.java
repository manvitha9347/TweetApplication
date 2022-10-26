package com.tweetapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class TweetExceptionHandler {

    @ExceptionHandler(value= { TweetsNotFoundException.class})
    public ResponseEntity<?> handleTweetsNotFoundException(TweetsNotFoundException e)
    {
        log.info("TweetsNotFoundException");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

  @ExceptionHandler(value= { UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e)
    {
        log.info("UserNotFoundException");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


    @ExceptionHandler(value= { NoSuchElementException.class})
    public ResponseEntity<?> noSuchElement(NoSuchElementException e)
    {
        log.info("UserNotFoundException");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such tweet found for given Id");
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleGlobalException(Exception e)
    {
        log.info("GlobalException");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
