package com.tweetapp.model;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "TweetAction")
public class TweetAction implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "action_sequence";

    @Id
    private int id;
    private int tweetId;

    @OneToMany(targetEntity = Likes.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "tweet_likes_id", referencedColumnName = "tweetId")
    private List<Likes> likes;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "tweet_comment_id", referencedColumnName = "tweetId")
    private List<Comment> comments;
}
