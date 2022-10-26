package com.tweetapp.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TweetsNotFoundExceptionTest {

    TweetsNotFoundException tweetsNotFoundException;
    @BeforeEach
    public void setup(){
        tweetsNotFoundException=new TweetsNotFoundException("Tweet not found");
    }

    @Test
    public void testTweetNotFound(){
        assertEquals("Tweet not found",tweetsNotFoundException.getMessage());
    }
}
