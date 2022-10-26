package com.tweetapp.service;

import com.tweetapp.model.UserEntity;
import com.tweetapp.repo.LoginRepo;
import com.tweetapp.response.UserDetailsRepsonse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private LoginRepo loginRepo;


    @Autowired
    public UserDetailsServiceImpl(LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity user = loginRepo.findByLoginId(username);
            if (user != null) {
                return new User(user.getLoginId(), user.getPassword(), new ArrayList<>());
            }
        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public UserDetailsRepsonse getAllUserDetails(String username) throws UsernameNotFoundException{
        try{
            UserEntity byLoginId = loginRepo.findByLoginId(username);
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            UserDetailsRepsonse mapped = modelMapper.map(byLoginId, UserDetailsRepsonse.class);
            if(mapped!=null){
                return mapped;
            }
        }catch(UsernameNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
