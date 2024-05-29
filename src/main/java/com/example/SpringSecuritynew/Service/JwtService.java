package com.example.SpringSecuritynew.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final String SECRECT_KEY="d63f925f8fa2e40a4b764ea5fd2085db65824fc9efa432752c488879e47466b5";


    public String GenerateToken(String name){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,name);

    }

    private String createToken( Map<String, Object> claims,String name) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getsingkey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getsingkey() {
        byte[]keybyte= Decoders.BASE64.decode(SECRECT_KEY);
        return Keys.hmacShaKeyFor(keybyte);
    }
}
