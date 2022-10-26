package com.tweetapp.service;

import com.tweetapp.model.Comment;
import com.tweetapp.model.TweetAction;
import com.tweetapp.request.CommentRequest;

import java.util.List;

public interface TweetService {
    public int likeTweet(String username,int tweetId);
    public TweetAction commentTweet(String username, int tweetId, CommentRequest commentRequest);
    public int getNumberOfLikes(int tweetId);
    public List<Comment> getCommentByTweetId(int tweetId);
}
