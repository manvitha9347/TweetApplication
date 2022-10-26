package com.tweetapp.controller;

import com.tweetapp.model.UserEntity;
import com.tweetapp.repo.LoginRepo;
import com.tweetapp.request.ForgetPassword;
import com.tweetapp.request.UserLogin;
import com.tweetapp.response.UserDetailsRepsonse;
import com.tweetapp.service.LoginService;
import com.tweetapp.service.UserDetailsServiceImpl;
import com.tweetapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/tweets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private LoginService loginService;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    public LoginController(LoginService loginService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/register")
    public String registerNewUser(@RequestBody UserEntity userEntity) {
        return loginService.registerUser(userEntity);
    }

    @PostMapping("/forgot/{loginId}")
    public String forgetPass(@PathVariable String loginId, @RequestBody ForgetPassword forgetPassword) throws Exception {
        return loginService.forgetPassword(loginId, forgetPassword);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getLoginId(), userLogin.getPassword())
        );
        if (auth != null) {
            return jwtUtil.generateToken(userLogin.getLoginId());
        } else {
            throw new BadCredentialsException("Invalid user");
        }

    }
    @GetMapping("/users")
    public HashSet<String> getUserDetails(){
        HashSet<String> usersIds = new HashSet<String>();
        List<UserEntity> allUsers = loginRepo.findAll();
        if(!allUsers.isEmpty()) {
            for (UserEntity i : allUsers) {
                usersIds.add(i.getLoginId());
            }
            return usersIds;
        }
        return  usersIds;
    }

    @GetMapping("/userDetails/{loginId}")
    public UserDetailsRepsonse getAllUserDetails(@PathVariable String loginId){
        UserDetailsRepsonse allUserDetails = userDetailsService.getAllUserDetails(loginId);
        return allUserDetails;
    }


}
