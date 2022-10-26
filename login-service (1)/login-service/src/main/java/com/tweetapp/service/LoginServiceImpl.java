package com.tweetapp.service;

import com.tweetapp.model.UserEntity;
import com.tweetapp.repo.LoginRepo;
import com.tweetapp.request.ForgetPassword;
import com.tweetapp.request.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private LoginRepo loginRepo;
    private BCryptPasswordEncoder encoder;
    private SequenceGeneratorService sequence;

    @Autowired
    public LoginServiceImpl(LoginRepo loginRepo, BCryptPasswordEncoder encoder, SequenceGeneratorService sequence) {
        this.loginRepo = loginRepo;
        this.encoder = encoder;
        this.sequence = sequence;
    }

    @Override
    public String registerUser(UserEntity userEntity) {
        if (userEntity != null) {
            userEntity.setId(sequence.getSequenceNumber(UserEntity.SEQUENCE_NAME));
            String password = encoder.encode(userEntity.getPassword());
            String confirmPassword = encoder.encode(userEntity.getConfirmPassword());
            userEntity.setPassword(password);
            userEntity.setConfirmPassword(confirmPassword);
            loginRepo.save(userEntity);
            return userEntity.getLoginId();
        }
        return null;
    }
    @Override
    public String forgetPassword(String loginId, ForgetPassword forgetPassword) throws Exception {
        if (loginId != null) {
            UserEntity userEntity = loginRepo.findByLoginId(loginId);
            if (userEntity != null) {
                if (userEntity.getEmail().equalsIgnoreCase(forgetPassword.getEmail())) {
                    userEntity.setPassword(encoder.encode(forgetPassword.getPassword()));
                    userEntity.setConfirmPassword(encoder.encode(forgetPassword.getConfirmPassword()));
                    loginRepo.save(userEntity);
                    return "You password changed";
                } else {
                   throw new Exception("EmailId Incorrect");
                }
            }
        }
        return null;
    }
}
