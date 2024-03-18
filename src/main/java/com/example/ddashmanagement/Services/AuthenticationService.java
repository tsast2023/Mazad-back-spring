package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Dto.JWTAuthenticationResponse;
import com.example.ddashmanagement.Dto.RefreshTokenRequest;
import com.example.ddashmanagement.Dto.SignInRequest;
import com.example.ddashmanagement.Dto.SignUpRequest;
import com.example.ddashmanagement.Entites.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    User SignUp(SignUpRequest signUpRequest);
    JWTAuthenticationResponse SignIn(SignInRequest signInRequest);

    JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
