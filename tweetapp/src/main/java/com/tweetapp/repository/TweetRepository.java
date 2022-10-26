package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.Tweet;

@Repository
public interface TweetRepository extends MongoRepository<Tweet,Integer>{

	List<Tweet> findByUserName(String userName);


//	public List<Tweet> getTweetByLoginId(String loginId);

}
