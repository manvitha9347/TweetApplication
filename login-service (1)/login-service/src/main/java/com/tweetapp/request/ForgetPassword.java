package com.tweetapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForgetPassword {
    private String email;
    private String password;
    private String confirmPassword;
}
