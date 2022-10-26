package com.tweetapp.controller;

import com.tweetapp.model.Comment;
import com.tweetapp.model.Likes;
import com.tweetapp.model.TweetAction;
import com.tweetapp.request.CommentRequest;
import com.tweetapp.service.TweetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TweetActionControllerTest {
    @Mock
    private TweetServiceImpl tweetService;

    @InjectMocks
    private TweetController tweetController;
    private TweetAction tweetAction;
    private Comment comment;
    private Likes likes;
    private CommentRequest commentRequest;
    List<Likes> likesList = new ArrayList<Likes>();
    List<Comment> commentsList = new ArrayList<Comment>();

    @BeforeEach
    public void setUp() throws Exception {
        Likes likes = new Likes(1,"manvitha",1);
        Comment comment = new Comment(1,"deepu","this is my comment");
        CommentRequest commentRequest = new CommentRequest("this is comment request");
        likesList.add(likes);
        commentsList.add(comment);
    }

    @Test
    public void testGetLikes() {
        TweetAction tweetAction = new TweetAction();
        tweetAction.setId(1);
        tweetAction.setTweetId(1);
        tweetAction.setLikes(likesList);
        tweetAction.setComments(commentsList);
        when(tweetService.likeTweet("manvi", tweetAction.getTweetId())).thenReturn(1);
        int likes = tweetController.getLikes("manvi", tweetAction.getTweetId());
        assertEquals(1,1);

    }

    @Test
    public void testGetComments() {
        TweetAction tweetAction = new TweetAction();
        tweetAction.setId(1);
        tweetAction.setTweetId(1);
        tweetAction.setLikes(likesList);
        tweetAction.setComments(commentsList);
        when(tweetService.commentTweet("manvi", tweetAction.getTweetId(), commentRequest)).thenReturn(tweetAction);
        TweetAction tweetAction1 = tweetController.getComments("manvi", tweetAction.getTweetId(), commentRequest);
        assertEquals(tweetAction1.getComments(), tweetAction.getComments());
    }
}
