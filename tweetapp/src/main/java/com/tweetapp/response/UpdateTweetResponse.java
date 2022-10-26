package com.tweetapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateTweetResponse {
    private int tweetId;
    private String tweet;
    private String tweetPostTime;
    private String tag;
}
