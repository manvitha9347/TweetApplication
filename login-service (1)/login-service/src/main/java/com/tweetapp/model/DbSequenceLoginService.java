package com.tweetapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_sequence_login_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSequenceLoginService {
    @Id
    private String  id;
    private int seq;
}