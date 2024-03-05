package com.example.ddashmanagement.Services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTServices {

    String ExtractUsername(String token);
    String generateToken(UserDetails userDetails) ;

    boolean isTokenValid(String token , UserDetails userDetails);
}
