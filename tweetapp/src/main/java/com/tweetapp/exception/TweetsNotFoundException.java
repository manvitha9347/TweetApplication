package com.tweetapp.exception;

public class TweetsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7665288457281499639L;
    private String message;
	public TweetsNotFoundException(String message) {
		super(message);
		this.message = message;
	}
    
    
}
