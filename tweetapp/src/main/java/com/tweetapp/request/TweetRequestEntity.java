package com.tweetapp.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetRequestEntity {

    private String tweet;

    private String tweetPostTime;

    private String userName;

    private String tag;
}
