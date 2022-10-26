package com.tweetapp.request;

import com.tweetapp.controller.TweetControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TweetRequestEntityTest {

    private TweetRequestEntity tweetRequestEntity;

    @BeforeEach
    public void setup() {
        tweetRequestEntity = new TweetRequestEntity();
        tweetRequestEntity.setTweet("hi");
        tweetRequestEntity.setUserName("Chetan");
        tweetRequestEntity.setTweetPostTime("now");
        tweetRequestEntity.setTag("#test");
    }

    @Test
    public void testGetter(){
        assertEquals("hi",tweetRequestEntity.getTweet());
        assertEquals("Chetan",tweetRequestEntity.getUserName());
        assertEquals("now",tweetRequestEntity.getTweetPostTime());
        assertEquals("#test",tweetRequestEntity.getTag());
    }

    @Test
    public void testSetter(){
        tweetRequestEntity.setTweet("hi");
        assertEquals("hi",tweetRequestEntity.getTweet());
    }

    @Test
    public void testAllArgsConstructor(){
        tweetRequestEntity = new TweetRequestEntity("hi","now","Chetan","#test");
        assertEquals("Chetan",tweetRequestEntity.getUserName());
    }
}
