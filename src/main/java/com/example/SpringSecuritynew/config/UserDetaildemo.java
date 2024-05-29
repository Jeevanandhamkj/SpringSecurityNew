package com.example.SpringSecuritynew.config;

import com.example.SpringSecuritynew.Entity.Userinfo;
import com.example.SpringSecuritynew.Repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserDetaildemo implements UserDetailsService {
    @Autowired
    UserInfoRepo userInfoRepo;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Userinfo>userinfo=userInfoRepo.findByName(name);
        return userinfo.map(UserDetailsDemo2::new).orElseThrow(()->new UsernameNotFoundException("notfound"));
    }
}
