package com.tweetapp.service;

import java.util.HashSet;
import java.util.List;

import com.tweetapp.dto.TweetDto;
import com.tweetapp.exception.TweetsNotFoundException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.model.Tweet;
import com.tweetapp.response.UpdateTweetResponse;

public interface TweetService {
	
	List<TweetDto> getAllTweet() throws TweetsNotFoundException;
	HashSet<String> getAllUsers();
	List<TweetDto> getAllTweetsOfUser(String userName) throws UserNotFoundException;
	Tweet postTweet(TweetDto tweet, String userName);
	UpdateTweetResponse updateTweet(Tweet tweet, String userName, int tweetId) throws TweetsNotFoundException;
	void deleteTweetbyId(Integer tweetId,String userName) throws TweetsNotFoundException;
	
}
