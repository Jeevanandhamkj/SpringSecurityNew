package com.example.SpringSecuritynew.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtService {

private final String SECRET_Key="KLli4NRL/dxiGEncJvrgAVID5bYjbB1Qu0L1INV1zQs=";
    public String generateToken(String name){

        Map<String ,Object> claims=new HashMap<>();
        return createToken(claims,name);
    }

    private String createToken(Map<String, Object> claims, String name) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSignkey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignkey() {
        byte[]keybite= Decoders.BASE64.decode(SECRET_Key);
        return Keys.hmacShaKeyFor(keybite);
    }
}
