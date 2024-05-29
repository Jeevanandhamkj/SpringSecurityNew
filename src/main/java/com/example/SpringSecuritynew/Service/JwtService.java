package com.example.SpringSecuritynew.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private final String SECRECT_KEY="d63f925f8fa2e40a4b764ea5fd2085db65824fc9efa432752c488879e47466b5";

  public String extractusername(String token){
      return extractClaims(token,Claims::getSubject);
  }
  public Date extractExpired(String token){
      return extractClaims(token,Claims::getExpiration);
  }



   public <T>T  extractClaims(String token, Function<Claims,T>ClaimsResolver){
       final Claims claims=extractAllClaims(token);
       return ClaimsResolver.apply(claims);
    }



   private Claims extractAllClaims(String token){
       return Jwts.parserBuilder()
               .setSigningKey(SECRECT_KEY)
               .build()
               .parseClaimsJws(token)
               .getBody();
   }


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
