package com.tweetapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7665288457281499639L;
    private String message;
	public UserNotFoundException(String message) {
		super(message);
		this.message = message;
	}
    
}
