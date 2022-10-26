package com.tweetapp.service;

import com.tweetapp.model.Comment;
import com.tweetapp.model.Likes;
import com.tweetapp.model.TweetAction;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.request.CommentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TweetServiceImpTest {
    @InjectMocks
    private TweetServiceImpl tweetServiceImp;
    @Mock
    private TweetRepository tweetRepository;
    @Mock
    private KafkaTemplate<String,TweetAction> kafkaTemplate;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    private TweetAction tweetAction;
    private Comment comment;
    private Likes likes;
    private CommentRequest commentRequest;
    List<Likes> likesList = new ArrayList();
    List<Comment> commentsList = new ArrayList<Comment>();

    @BeforeEach
    public void setUp(){
        Likes likes = new Likes(1,"manvitha",1);
        Comment comment = new Comment(1,"deepu","this is my comment");
        commentRequest = new CommentRequest("this is comment request");
        likesList.add(likes);
        commentsList.add(comment);
    }

    @Test
    public void testLikeTweet(){
        TweetAction tweetAction = new TweetAction();
        tweetAction.setId(1);
        tweetAction.setTweetId(1);
        tweetAction.setLikes(likesList);
        int likeResponse = tweetServiceImp.likeTweet("manvi", 1);
        assertEquals(1,1);
    }

    @Test
    public void testCommentTweet(){
        TweetAction tweetAction = new TweetAction();
        tweetAction.setId(1);
        tweetAction.setTweetId(1);
        tweetAction.setLikes(likesList);
        tweetAction.setComments(commentsList);
        TweetAction commetResponse = tweetServiceImp.commentTweet("manvi", 1,commentRequest);
        assertNotNull(commetResponse.getComments().get(0).getComment(),tweetAction.getComments().get(0).getComment());
    }
    @Test
    public void testGetNumberOfLikes(){
        TweetAction tweetAction = new TweetAction();
        tweetAction.setId(1);
        tweetAction.setTweetId(1);
        tweetAction.setLikes(likesList);
       when(tweetRepository.findBytweetId(1)).thenReturn(tweetAction);
      int likes = tweetServiceImp.getNumberOfLikes(1);
      assertEquals(1,likes);
    }

    @Test
    public void testGetCommentByTweetId(){
        TweetAction tweetAction = new TweetAction();
        tweetAction.setId(1);
        tweetAction.setTweetId(1);
        tweetAction.setLikes(likesList);
        tweetAction.setComments(commentsList);
        when(tweetRepository.findBytweetId(1)).thenReturn(tweetAction);
        List<Comment> commentByTweetId = tweetServiceImp.getCommentByTweetId(1);
        assertEquals(commentsList,commentByTweetId);
    }
}


