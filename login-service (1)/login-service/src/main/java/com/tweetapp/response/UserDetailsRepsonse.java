package com.tweetapp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailsRepsonse {
    private String firstName;
    private String lastName;
    private String email;
    private String loginId;
    private String contactNumber;
}
