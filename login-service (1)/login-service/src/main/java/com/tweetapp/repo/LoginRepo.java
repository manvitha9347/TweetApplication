package com.tweetapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.UserEntity;

@Repository
public interface LoginRepo extends MongoRepository<UserEntity,Integer> {
    public UserEntity findByLoginId(String loginId);
}
