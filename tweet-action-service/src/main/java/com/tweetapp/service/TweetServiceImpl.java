package com.tweetapp.service;


import com.tweetapp.model.Comment;
import com.tweetapp.model.Likes;
import com.tweetapp.model.TweetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.request.CommentRequest;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepo;
    @Autowired
    KafkaTemplate<String, TweetAction> kafkaTemplate;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;



    @Override
    public int likeTweet(String username, int tweetId) {
        TweetAction tweetActionByTweetId = tweetRepo.findBytweetId(tweetId);
        if (tweetActionByTweetId != null) {
            List<Likes> exsistingLikes = tweetActionByTweetId.getLikes();
            for (Likes likes : exsistingLikes) {
                String exsistingUsername = likes.getUsername();
                if (username.equalsIgnoreCase(exsistingUsername)) {
                    return exsistingLikes.size();
                }
            }
            Likes newLikes = new Likes();
            newLikes.setId(sequenceGeneratorService.getSequenceNumber(Likes.SEQUENCE_NAME));
            newLikes.setUsername(username);
            newLikes.setLikes(1);
            exsistingLikes.add(newLikes);
            kafkaTemplate.send("DemoTopic", tweetActionByTweetId);
            return tweetActionByTweetId.getLikes().size();
        } else {
            TweetAction tweetAction = new TweetAction();
            tweetAction.setId(sequenceGeneratorService.getSequenceNumber(TweetAction.SEQUENCE_NAME));
            Likes likes = new Likes();
            likes.setId(sequenceGeneratorService.getSequenceNumber(Likes.SEQUENCE_NAME));
            likes.setLikes(1);
            likes.setUsername(username);
            tweetAction.setTweetId(tweetId);
            List<Likes> likesList = new ArrayList<>();
            likesList.add(likes);
            tweetAction.setLikes(likesList);
            kafkaTemplate.send("DemoTopic", tweetAction);
            return tweetAction.getLikes().size();
        }

    }


    @KafkaListener(groupId = "tweet-action-group", topics = "DemoTopic", containerFactory = "kafkaListenerContainerFactory")
    public TweetAction kafkaListener(TweetAction tweetAction) {
        TweetAction save = tweetRepo.save(tweetAction);
        return save;
    }

    @Override
    @Transactional
    public TweetAction commentTweet(String username, int tweetId, CommentRequest commentRequest) {
        TweetAction tweetActionByTweetId = tweetRepo.findBytweetId(tweetId);
        List<Comment> commentsList = new ArrayList<>();
        if (tweetActionByTweetId != null) {
            Comment comment = new Comment();
            List<Comment> exsistingComments = tweetActionByTweetId.getComments();
            comment.setUserName(username);
            comment.setComment(commentRequest.getComment());
            comment.setId(sequenceGeneratorService.getSequenceNumber(Comment.SEQUENCE_NAME));
            if (exsistingComments==null) {
                commentsList.add(comment);
                tweetActionByTweetId.setComments(commentsList);
            } else {

                exsistingComments.add(comment);
                tweetActionByTweetId.setComments(exsistingComments);
            }
            kafkaTemplate.send("DemoTopic", tweetActionByTweetId);
            return tweetActionByTweetId;
        } else {
            Comment comment = new Comment();
            TweetAction tweetAction = new TweetAction();
            tweetAction.setId(sequenceGeneratorService.getSequenceNumber(TweetAction.SEQUENCE_NAME));
            comment.setId(sequenceGeneratorService.getSequenceNumber(Comment.SEQUENCE_NAME));
            comment.setUserName(username);
            comment.setComment(commentRequest.getComment());
            commentsList.add(comment);
            tweetAction.setTweetId(tweetId);
            tweetAction.setComments(commentsList);
            kafkaTemplate.send("DemoTopic", tweetAction);
            return tweetAction;
        }

    }
    public int getNumberOfLikes(int tweetId){
        TweetAction tweetAction = tweetRepo.findBytweetId(tweetId);
        return tweetAction.getLikes().size();
    }

    public List<Comment> getCommentByTweetId(int tweetId){
        TweetAction tweetAction = tweetRepo.findBytweetId(tweetId);
        return tweetAction.getComments();
    }
}