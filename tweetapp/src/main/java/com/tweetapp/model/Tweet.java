package com.tweetapp.model;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Tweet")
public class Tweet {

    @Transient
    public static final String SEQUENCE_NAME = "tweet_sequence";

    @Id
    @Column(name = "tweetId")
    private int tweetId;

    @Column(length = 144)
    private String tweet;

    private String tweetPostTime;

    private String userName;

    @Column(length = 50)
    private String tag;

}
