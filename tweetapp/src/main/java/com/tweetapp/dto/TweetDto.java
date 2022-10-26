package com.tweetapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TweetDto {
	
	private Integer tweetId;
	
	private String tweet;

	private String tweetPostTime;

	private String userName;

	private String tag;
}
