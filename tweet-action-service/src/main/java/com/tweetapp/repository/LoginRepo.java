package com.tweetapp.repository;

import com.tweetapp.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends MongoRepository<UserEntity,Integer> {
    public UserEntity findByLoginId(String loginId);
}
