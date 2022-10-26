package com.tweetapp.controller;

import com.tweetapp.model.UserEntity;
import com.tweetapp.request.ForgetPassword;
import com.tweetapp.request.UserLogin;
import com.tweetapp.service.LoginServiceImpl;
import com.tweetapp.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestLoginController {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginServiceImpl loginService;
    @Mock
    private JwtUtil jwtUtil;

    private UserEntity userEntity;
    private UserLogin userLogin;
    private ForgetPassword forgetPassword;
    @BeforeEach
    public void setUp(){
        userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setLoginId("yandas123");
        userEntity.setEmail("abcd@gmail.com");
        userEntity.setPassword("ABCD");
        userEntity.setConfirmPassword("ABCD");
        userEntity.setContactNumber("99893484");

        forgetPassword = new ForgetPassword();
        forgetPassword.setPassword("ABCD");
        forgetPassword.setConfirmPassword("ABCD");


    }

    @Test
    public void registerUser(){
        when(loginService.registerUser(userEntity)).thenReturn("yandas123");
        String username = loginController.registerNewUser(userEntity);
        assertEquals(username,userEntity.getLoginId());
    }

    @Test
    public void forgetPassword() throws Exception {
        when(loginService.forgetPassword("yandas123",forgetPassword)).thenReturn("Password Changed");
        String response =loginController.forgetPass("yandas123",forgetPassword);
        assertEquals(response,"Password Changed");
    }
}
