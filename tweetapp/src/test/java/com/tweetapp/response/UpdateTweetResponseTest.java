package com.tweetapp.response;

import com.tweetapp.dto.TweetDto;
import com.tweetapp.model.Tweet;
import com.tweetapp.request.TweetRequestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UpdateTweetResponseTest {

    UpdateTweetResponse updateTweetResponse;
    @BeforeEach
    public void setUp() throws Exception {
        updateTweetResponse = new UpdateTweetResponse();
        updateTweetResponse.setTweetId(1);
        updateTweetResponse.setTweet("hi");
        updateTweetResponse.setTweetPostTime("now");
        updateTweetResponse.setTag("#test");
    }

    @Test
    public void testGetters(){
        assertEquals(1,updateTweetResponse.getTweetId());
        assertEquals("hi",updateTweetResponse.getTweet());
        assertEquals("now",updateTweetResponse.getTweetPostTime());
        assertEquals("#test",updateTweetResponse.getTag());
    }

    @Test
    public void testSetters(){
        updateTweetResponse.setTweet("hello");
        assertEquals("hello",updateTweetResponse.getTweet());
    }

    @Test
    public void testAllArgsConstructor(){
        updateTweetResponse=new UpdateTweetResponse(1,"hi","now","#test");
        assertEquals("hi",updateTweetResponse.getTweet());
    }
}
