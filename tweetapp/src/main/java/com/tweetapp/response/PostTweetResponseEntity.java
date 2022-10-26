package com.tweetapp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostTweetResponseEntity {
	private int tweetId;

	private String tweet;

	private String tweetPostTime;

	private String userName;

	private String tag;
}
