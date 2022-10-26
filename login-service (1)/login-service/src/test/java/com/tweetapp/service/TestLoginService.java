package com.tweetapp.service;

import com.tweetapp.model.UserEntity;
import com.tweetapp.repo.LoginRepo;
import com.tweetapp.request.ForgetPassword;
import com.tweetapp.request.UserLogin;
import com.tweetapp.request.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestLoginService {
    @Mock
    public LoginRepo loginRepo;
    @Mock
    private SequenceGeneratorService sequence;
    @Mock
    private BCryptPasswordEncoder encoder;
    @InjectMocks
    public LoginServiceImpl loginServiceImpl;
    private UserEntity userEntity;
    private UserLogin userLogin;
    private ForgetPassword forgetPassword;

    @BeforeEach
    public void setup() throws  Exception{
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
    public void testRegistration(){
      String userId =  loginServiceImpl.registerUser(userEntity);
      assertEquals(userId,"yandas123");
    }
}
