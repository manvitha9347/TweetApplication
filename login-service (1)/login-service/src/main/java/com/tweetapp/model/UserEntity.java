package com.tweetapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="UserEntity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
	
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";
	
    @Id
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String loginId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String confirmPassword;
    @Column(nullable = false)
    private String contactNumber;
}
