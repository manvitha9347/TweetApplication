package com.tweetapp.controller;

import com.tweetapp.model.Comment;
import com.tweetapp.model.TweetAction;
import com.tweetapp.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tweetapp.service.TweetServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1.0/tweets/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TweetController {

    @Autowired
    private TweetServiceImpl tweetServiceImpl;

    @PostMapping("{username}/likes/{tweetId}")
    public int getLikes(@PathVariable String username, @PathVariable int tweetId) {
        return tweetServiceImpl.likeTweet(username, tweetId);
    }

    @PostMapping("{username}/comment/{tweetId}")
    public TweetAction getComments(@PathVariable String username, @PathVariable int tweetId, @RequestBody CommentRequest commentRequest) {
        return tweetServiceImpl.commentTweet(username, tweetId, commentRequest);
    }

    @GetMapping("likes/{tweetId}")
    public int getNumberOfLikes(@PathVariable int tweetId) {
        return tweetServiceImpl.getNumberOfLikes(tweetId);
    }

    @GetMapping("/comment/{tweetId}")
    public List<Comment> getCommentByTweetId(@PathVariable int tweetId){
        return tweetServiceImpl.getCommentByTweetId(tweetId);
    }

}
