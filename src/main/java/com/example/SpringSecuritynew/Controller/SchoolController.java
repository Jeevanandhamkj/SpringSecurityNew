package com.example.SpringSecuritynew.Controller;

import com.example.SpringSecuritynew.Dto.AuthRequest;
import com.example.SpringSecuritynew.Entity.Userinfo;
import com.example.SpringSecuritynew.Service.JwtService;
import com.example.SpringSecuritynew.Service.UserInfoService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kj")
public class SchoolController {

    @Autowired
    UserInfoService userInfoService;
@Autowired
    JwtService jwtService;


    @Autowired
    AuthenticationManager authenticationManager;


    @GetMapping("/Hm")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public String headMaster() {
        return "This page only access by headmaster";
    }

    @GetMapping("/Tea")
    @PreAuthorize("hasAuthority('ROLE_teacher')")
    public String Teacher() {
        return "This page only access by Teacher";
    }

    @GetMapping("/stu")
    @PreAuthorize("hasAuthority('ROLE_student')")
    public String Student() {
        return "This page only access by students";
    }

    @PostMapping("/add")
    public String usernewadd(@RequestBody Userinfo userinfo) {

        return userInfoService.adduserinfo(userinfo);
    }

    @PostMapping("/a")
    public String authtoken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.GenerateToken(authRequest.getName());
            } else {
                throw new UsernameNotFoundException("not found");
            }


        } catch (Exception e) {
            throw new UsernameNotFoundException("not found");
        }

    }
}
