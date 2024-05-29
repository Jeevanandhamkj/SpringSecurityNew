package com.example.SpringSecuritynew.Service;

import com.example.SpringSecuritynew.Entity.Userinfo;
import com.example.SpringSecuritynew.Repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepo userInfoRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String adduserinfo(Userinfo userinfo){
        userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));
         userInfoRepo.save(userinfo);
         return "user added";
    }
}
