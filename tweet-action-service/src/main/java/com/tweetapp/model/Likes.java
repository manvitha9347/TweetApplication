package com.tweetapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection="Likes")
public class Likes {
	
	@Transient
	public static final String SEQUENCE_NAME = "likes_sequence";
	
    @Id
    private int id;
    private String username;
    private int likes;
}
