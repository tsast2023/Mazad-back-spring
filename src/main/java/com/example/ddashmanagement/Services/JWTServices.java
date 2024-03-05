package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JWTServices {

    String ExtractUsername(String token);
    String generateToken(UserDetails userDetails) ;

    boolean isTokenValid(String token , UserDetails userDetails);


    String generateRefreshToken(Map<String , Object> extractClaims, UserDetails userDetails);
}
