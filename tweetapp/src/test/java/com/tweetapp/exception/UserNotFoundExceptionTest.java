package com.tweetapp.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class UserNotFoundExceptionTest {
    UserNotFoundException userNotFoundException;

    @BeforeEach
    public void setup(){
        userNotFoundException=new UserNotFoundException("User not found");
    }

    @Test
    public void testUserNotFound(){
        assertEquals("User not found",userNotFoundException.getMessage());
    }
}
