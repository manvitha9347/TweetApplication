package com.tweetapp.response;

import com.tweetapp.dto.TweetDto;
import com.tweetapp.model.Tweet;
import com.tweetapp.request.TweetRequestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PostTweetResponseEntityTest {

    PostTweetResponseEntity postTweetResponseEntity;

    @BeforeEach
    public void setUp() throws Exception {
        postTweetResponseEntity = new PostTweetResponseEntity();
        postTweetResponseEntity.setTweetId(1);
        postTweetResponseEntity.setTweet("hi");
        postTweetResponseEntity.setUserName("Chetan");
        postTweetResponseEntity.setTweetPostTime("now");
        postTweetResponseEntity.setTag("#test");
    }

    @Test
    public void testGetter(){
        assertEquals("hi",postTweetResponseEntity.getTweet());
    }

    @Test
    public void testSetter(){
        postTweetResponseEntity.setTweet("hi");
        assertEquals("hi",postTweetResponseEntity.getTweet());
    }

    @Test
    public void testAllArgsConstructor(){
        PostTweetResponseEntity postTweetResponseEntity1=new PostTweetResponseEntity(1,"hi","now","Chetan","#test");
        assertEquals("hi",postTweetResponseEntity1.getTweet());
    }
}
