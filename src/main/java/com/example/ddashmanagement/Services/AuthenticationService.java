package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Dto.SignUpRequest;
import com.example.ddashmanagement.Entites.User;

public interface AuthenticationService {
    User SignUp(SignUpRequest signUpRequest);
}
