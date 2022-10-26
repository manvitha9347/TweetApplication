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
@Document(collection="Comment")
public class Comment {
	
	@Transient
	public static final String SEQUENCE_NAME = "comment_sequence";
	
    @Id
    private int id;
    private String comment;
    private String userName;
}
