package com.tweetapp.controller;

import com.tweetapp.dto.TweetDto;
import com.tweetapp.exception.TweetsNotFoundException;
import com.tweetapp.model.Tweet;
import com.tweetapp.request.TweetRequestEntity;
import com.tweetapp.response.PostTweetResponseEntity;
import com.tweetapp.response.UpdateTweetResponse;
import com.tweetapp.service.TweetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TweetControllerTest {

    @Mock
    private TweetServiceImpl service;

    @InjectMocks
    private TweetController controller;
    
    @Mock
    private ModelMapper modelMapper;

    private TweetDto tweetDto;
    private Tweet tweet;

    private TweetRequestEntity tweetRequestEntity;

    private PostTweetResponseEntity postTweetResponseEntity;

    private UpdateTweetResponse updateTweetResponse;
   

    List<Tweet> tweetList=new ArrayList<Tweet>();
    List<TweetDto> tweetDtoList=new ArrayList<TweetDto>();
    HashSet<String> tweets = new HashSet<String>();

    @BeforeEach
    public void setUp() throws Exception{
        tweet=new Tweet();
        tweet.setTweetId(1);
        tweet.setTweet("hi");
        tweet.setUserName("Chetan");
        tweet.setTweetPostTime("now");
        tweet.setTag("#test");

        tweetDto=new TweetDto();
        tweetDto.setTweetId(1);
        tweetDto.setTweet("hi");
        tweetDto.setUserName("Chetan");
        tweetDto.setTweetPostTime("now");
        tweetDto.setTag("#test");

        tweetRequestEntity=new TweetRequestEntity();
        tweetRequestEntity.setTweet("hi");
        tweetRequestEntity.setUserName("Chetan");
        tweetRequestEntity.setTweetPostTime("now");
        tweetRequestEntity.setTag("#test");

        postTweetResponseEntity=new PostTweetResponseEntity();
        postTweetResponseEntity.setTweetId(1);
        postTweetResponseEntity.setTweet("hi");
        postTweetResponseEntity.setUserName("Chetan");
        postTweetResponseEntity.setTweetPostTime("now");
        postTweetResponseEntity.setTag("#test");

        updateTweetResponse=new UpdateTweetResponse();
        updateTweetResponse.setTweetId(1);
        updateTweetResponse.setTweet("hi");
        updateTweetResponse.setTweetPostTime("now");
        updateTweetResponse.setTag("#test");


        tweetList.add(tweet);

        tweetDtoList.add(tweetDto);

        tweets.add(tweet.getUserName());
    }

    @Test
    public void testGetAllTweets(){
        when(service.getAllTweet()).thenReturn(tweetDtoList);
        ResponseEntity<List<PostTweetResponseEntity>> allTweets = controller.getAllTweets();
        assertEquals(allTweets.getBody().get(0).getTweet(),tweetDtoList.get(0).getTweet());
    }

    @Test
    public void testGetAllUsers(){
        when(service.getAllUsers()).thenReturn(tweets);
        ResponseEntity<HashSet<String>> allUsers = controller.getAllUsers();
        assertEquals(allUsers.getBody(),tweets);
    }

    @Test
    public void testGetAllTweetsByUserName(){
        when(service.getAllTweetsOfUser(tweet.getUserName())).thenReturn(tweetDtoList);
        ResponseEntity<List<TweetDto>> allTweetsByUserName = controller.getAllTweetsByUserName(tweet.getUserName());
        assertEquals(allTweetsByUserName.getBody(),tweetDtoList);
    }

//    @Test
//    public void testPostTweet(){
//        System.out.println(tweetRequestEntity);
//
//        ResponseEntity<PostTweetResponseEntity> postTweetResponseEntityResponseEntity = controller.postTweet(tweetRequestEntity,tweet.getUserName());
//
//        assertEquals(postTweetResponseEntityResponseEntity.getBody().getTweet(),postTweetResponseEntity.getTweet());
//    }

    @Test
    public void testUpdateTweet(){
        when(service.updateTweet(tweet,tweet.getUserName(),tweet.getTweetId())).thenReturn(updateTweetResponse);
        ResponseEntity<UpdateTweetResponse> updateTweetResponseResponseEntity = controller.updateTweet(tweet, tweet.getTweetId(), tweet.getUserName());
        assertEquals(updateTweetResponseResponseEntity.getBody().getTweet(),tweet.getTweet());
    }

    @Test
    public void testDeleteTweet(){
        ResponseEntity<String> stringResponseEntity = controller.deleteTweet(tweet.getTweetId(), tweet.getUserName());
        assertEquals(stringResponseEntity.getBody(),"Tweet Deleted Successfully");
    }
}
