package com.tweetapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DB_Sequence_TweetApp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSequenceTweetService {
    @Id
    private String  id;
    private int seq;
}