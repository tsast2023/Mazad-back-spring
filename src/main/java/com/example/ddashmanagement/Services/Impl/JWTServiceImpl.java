package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Services.JWTServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl  implements JWTServices {
    @Value("${jwt-secret}")
    private String secretKey ;
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000 * 60 * 24))
                .signWith(getSignInKey() , SignatureAlgorithm.HS256)
                .compact();

    }
    public String generateRefreshToken(Map<String , Object> extractClaims,UserDetails userDetails) {
        return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSignInKey() , SignatureAlgorithm.HS256)
                .compact();

    }
    public String ExtractUsername(String token) {
         return extractClaim(token , Claims::getSubject);
    }
    private <T> T extractClaim(String token , Function<Claims, T > claimsResolvers) {
        final Claims claims =  extractAllClaims(token);
        return claimsResolvers.apply(claims);

    }
    private Key getSignInKey(){
        byte[] key = Decoders.BASE64.decode("162160f61c41977c51681746756d5de28f1b13879cef0d3b7ebd2b7724c2caf2cab46d16e67b187ccc7dc4f275ec916c7760b3a4544bd835b8829aa42c395ba7");
        return Keys.hmacShaKeyFor(key);
    }
    private Claims extractAllClaims(String token){
       return Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }
    private boolean isTokenExpired(String token ){

        return extractClaim(token , Claims::getExpiration).before(new Date());
    }

    public boolean isTokenValid(String token , UserDetails userDetails) {
      final String username = ExtractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }


}
