package com.tweetapp.repository;

import java.util.List;

import com.tweetapp.model.TweetAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends MongoRepository<TweetAction, Integer> {
    public TweetAction findBytweetId(int tweetId);
}
