package com.tweetapp.service;

import java.util.*;

import com.tweetapp.exception.TweetsNotFoundException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.response.UpdateTweetResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.TweetDto;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.TweetRepository;

@Service
public class TweetServiceImpl implements TweetService {

	private TweetRepository tweetRepo;
	private SequenceGeneratorService sequence;
	KafkaTemplate<String,Tweet> kafkaTemplate;
	@Autowired
	public TweetServiceImpl(TweetRepository tweetRepo, SequenceGeneratorService sequence, KafkaTemplate<String, Tweet> kafkaTemplate) {
		this.tweetRepo = tweetRepo;
		this.sequence = sequence;
		this.kafkaTemplate = kafkaTemplate;
	}

	@KafkaListener(groupId = "my-tweet-group",topics = "DemoTopic2",containerFactory = "kafkaListenerContainerFactory")
	public Tweet kafkaListener(Tweet myTweet){
		Tweet save = tweetRepo.save(myTweet);
		return save;
	}
	@Override
	public List<TweetDto> getAllTweet() throws TweetsNotFoundException {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			List<TweetDto> tweets = new ArrayList<TweetDto>();
			List<Tweet> allTweetData = tweetRepo.findAll();
			if (!allTweetData.isEmpty()) {
				Iterator<Tweet> iterator = allTweetData.iterator();
				while (iterator.hasNext()) {
					tweets.add(modelMapper.map(iterator.next(), TweetDto.class));
				}
				return tweets;
			}
			else {
				throw new TweetsNotFoundException("No Tweets available currently. Please try again later");
			}
	}

	@Override
	public HashSet<String> getAllUsers() throws TweetsNotFoundException{
		HashSet<String> tweets = new HashSet<String>();
		List<Tweet> allTweetData = tweetRepo.findAll();
		if(!allTweetData.isEmpty()) {
			for (Tweet i : allTweetData) {
				tweets.add(i.getUserName());
			}
			return tweets;
		}else{
			throw new TweetsNotFoundException("No users found");
		}
	}
	
	@Override
	public List<TweetDto> getAllTweetsOfUser(String userName) throws UserNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<TweetDto> tweets = new ArrayList<TweetDto>();
			List<Tweet> name = tweetRepo.findByUserName(userName);
			if (name.size()!=0) {
				Iterator<Tweet> iterator = name.iterator();
				while (iterator.hasNext()) {
					tweets.add(modelMapper.map(iterator.next(), TweetDto.class));
				}
				return tweets;
			}
		else{
			throw new UserNotFoundException("No tweets found of the user");
		}
	}

	@Override
	public Tweet postTweet(TweetDto tweet, String userName) {
		tweet.setTweetId(sequence.getSequenceNumber(Tweet.SEQUENCE_NAME));
		tweet.setUserName(userName);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Tweet tweetSaved = modelMapper.map(tweet, Tweet.class);
		kafkaTemplate.send("DemoTopic2",tweetSaved);
		return tweetSaved;
	}

	@Override
	public UpdateTweetResponse updateTweet(Tweet tweet, String userName, int tweetId){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Tweet existingTweet = tweetRepo.findById(tweetId).get();
			existingTweet.setTweet(tweet.getTweet());
			existingTweet.setTag(tweet.getTag());
			existingTweet.setTweetPostTime(tweet.getTweetPostTime());
			kafkaTemplate.send("DemoTopic2",existingTweet);
			UpdateTweetResponse updateTweetResponse = modelMapper.map(existingTweet, UpdateTweetResponse.class);
			return updateTweetResponse;
	}

	@Override
	public void deleteTweetbyId(Integer tweetId, String userName){
		Tweet tweet = tweetRepo.findById(tweetId).get();
			tweetRepo.delete(tweet);

	}
}
