package com.tweetapp.service;

import com.tweetapp.model.UserEntity;
import com.tweetapp.request.ForgetPassword;
import com.tweetapp.request.UserLogin;

public interface LoginService {
    public String registerUser(UserEntity userEntity);
    public String forgetPassword(String loginId,ForgetPassword forgetPassword) throws Exception;
}
