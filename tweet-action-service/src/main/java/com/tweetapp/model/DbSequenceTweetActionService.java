package com.tweetapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TweetActionService")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSequenceTweetActionService {
    @Id
    private String  id;
    private int seq;
}