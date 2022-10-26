package com.tweetapp.service;

import com.tweetapp.dto.TweetDto;
import com.tweetapp.exception.TweetsNotFoundException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.response.UpdateTweetResponse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TweetServiceImplTest {

    @Mock
    private TweetRepository repo;

    @InjectMocks
    private TweetServiceImpl service;

    @Mock
    private SequenceGeneratorService sequence;
    
    @Mock
    private KafkaTemplate<String, Tweet> kafkaTemplate;

    private TweetDto tweetDto;
    private Tweet tweet;

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

        updateTweetResponse=new UpdateTweetResponse();
        updateTweetResponse.setTweetId(1);
        updateTweetResponse.setTweet("hi");
        updateTweetResponse.setTweetPostTime("now");
        updateTweetResponse.setTag("#test");

        tweetList.add(tweet);

        tweetDtoList.add(tweetDto);
    }
    @Test
    public void testKafkaListener(){
        when(repo.save(tweet)).thenReturn(tweet);
        assertEquals(service.kafkaListener(tweet).getTweet(),tweet.getTweet());
    }

    @Test
    public void testGetAllTweet() throws TweetsNotFoundException{
        when(repo.findAll()).thenReturn(tweetList);
        List<TweetDto> allTweet = service.getAllTweet();
        assertEquals(allTweet.get(0).getTweet(),tweetDtoList.get(0).getTweet());
    }

    @Test
    public void testGetAllUsers() throws UserNotFoundException{
        for (Tweet i : tweetList) {
            tweets.add(i.getUserName());
        }
        when(repo.findAll()).thenReturn(tweetList);
        assertEquals(service.getAllUsers(),tweets);
    }

    @Test
    public void testGetAllTweetsOfUser() throws UserNotFoundException{
        when(repo.findByUserName(tweet.getUserName())).thenReturn(tweetList);
        assertEquals(service.getAllTweetsOfUser(tweet.getUserName()).get(0).getTweet(),tweetDtoList.get(0).getTweet());
    }

    @Test
    public void testPostTweet(){
     //   tweet.setTweetId(sequence.getSequenceNumber(Tweet.SEQUENCE_NAME));
        assertEquals(service.postTweet(tweetDto,tweet.getUserName()).getTweet(),tweet.getTweet());
    }

    @Test
    public void testUpdateTweet() {
        when(repo.findById(tweet.getTweetId())).thenReturn(Optional.of(tweet));
        assertEquals(service.updateTweet(tweet,tweet.getUserName(),tweet.getTweetId()).getTweet(),updateTweetResponse.getTweet());
    }

}
