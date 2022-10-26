package com.tweetapp.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TweetExceptionHandlerTest {

    @InjectMocks
    private TweetExceptionHandler exceptionHandler;

    @Test
    public void testHandleTweetsNotFoundException(){
        TweetsNotFoundException tweetsNotFoundException=new TweetsNotFoundException("Tweet not found");
        ResponseEntity<?> responseEntity = exceptionHandler.handleTweetsNotFoundException(tweetsNotFoundException);
        assertEquals(responseEntity.getBody(),tweetsNotFoundException.getMessage());
    }

    @Test
    public void testHandleUserNotFoundException(){
        UserNotFoundException userNotFoundException=new UserNotFoundException("User not found");
        ResponseEntity<?> responseEntity = exceptionHandler.handleUserNotFoundException(userNotFoundException);
        assertEquals(responseEntity.getBody(),userNotFoundException.getMessage());
    }

    @Test
    public void testHandleGlobalException(){
        Exception exception=new Exception("Exception");
        ResponseEntity<?> responseEntity = exceptionHandler.handleGlobalException(exception);
        assertEquals(responseEntity.getBody(),exception.getMessage());
    }
}
