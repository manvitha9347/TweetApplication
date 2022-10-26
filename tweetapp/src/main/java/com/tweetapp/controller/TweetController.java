package com.tweetapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.tweetapp.model.UserEntity;
import com.tweetapp.repository.LoginRepo;
import com.tweetapp.request.TweetRequestEntity;
import com.tweetapp.response.UpdateTweetResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tweetapp.dto.TweetDto;
import com.tweetapp.model.Tweet;
import com.tweetapp.response.PostTweetResponseEntity;
import com.tweetapp.service.TweetServiceImpl;

@RestController
@RequestMapping(path = "/api/v1.0/tweets/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TweetController {

    private TweetServiceImpl tweetServiceImpl;
    private LoginRepo repo;

    @Autowired
    public TweetController(TweetServiceImpl tweetServiceImpl, LoginRepo repo) {
        this.tweetServiceImpl = tweetServiceImpl;
        this.repo = repo;
    }

    @GetMapping("all")
    public ResponseEntity<List<PostTweetResponseEntity>> getAllTweets() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<TweetDto> allTweet = tweetServiceImpl.getAllTweet();
        ArrayList<PostTweetResponseEntity> responses = new ArrayList<PostTweetResponseEntity>();
        for (TweetDto i : allTweet) {
            responses.add(modelMapper.map(i, PostTweetResponseEntity.class));
        }
        return new ResponseEntity<List<PostTweetResponseEntity>>(responses, HttpStatus.OK);
    }

    @GetMapping("users/all")
    public ResponseEntity<HashSet<String>> getAllUsers() {
        HashSet<String> allUsers = tweetServiceImpl.getAllUsers();
        return new ResponseEntity<HashSet<String>>(allUsers, HttpStatus.OK);
    }

    @GetMapping("{userName}")
    public ResponseEntity<List<TweetDto>> getAllTweetsByUserName(@PathVariable String userName) {
        List<TweetDto> allTweetsOfUser = tweetServiceImpl.getAllTweetsOfUser(userName);
        return new ResponseEntity<List<TweetDto>>(allTweetsOfUser, HttpStatus.OK);
    }

    @PostMapping("{userName}/add")
    public ResponseEntity<PostTweetResponseEntity> postTweet(@RequestBody TweetRequestEntity tweet, @PathVariable String userName) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TweetDto tweetDto = modelMapper.map(tweet, TweetDto.class);
        Tweet tweet1 = tweetServiceImpl.postTweet(tweetDto, userName);
        PostTweetResponseEntity responseEntity = modelMapper.map(tweet1, PostTweetResponseEntity.class);
        return new ResponseEntity<PostTweetResponseEntity>(responseEntity, HttpStatus.CREATED);
    }

    @PutMapping(path = "{userName}/update/{tweetId}")
    public ResponseEntity<UpdateTweetResponse> updateTweet(@RequestBody Tweet tweet, @PathVariable int tweetId, @PathVariable String userName) {
        UpdateTweetResponse updatedTweet = tweetServiceImpl.updateTweet(tweet, userName, tweetId);
        return new ResponseEntity<UpdateTweetResponse>(updatedTweet, HttpStatus.OK);
    }

    @DeleteMapping(path = "{userName}/delete/{tweetId}")
    public ResponseEntity<String> deleteTweet(@PathVariable Integer tweetId, @PathVariable String userName) {
        tweetServiceImpl.deleteTweetbyId(tweetId, userName);
        return new ResponseEntity<String>("Tweet Deleted Successfully", HttpStatus.OK);
    }

}
