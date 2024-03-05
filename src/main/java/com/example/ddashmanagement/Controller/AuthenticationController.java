package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Dto.JWTAuthenticationResponse;
import com.example.ddashmanagement.Dto.RefreshTokenRequest;
import com.example.ddashmanagement.Dto.SignInRequest;
import com.example.ddashmanagement.Dto.SignUpRequest;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService ;

    @PostMapping("/SignUp")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
       return ResponseEntity.ok(authenticationService.SignUp(signUpRequest));
    }

    @PostMapping("/SignIn")
    public ResponseEntity<JWTAuthenticationResponse> SignIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.SignIn(signInRequest));
    }

    @PostMapping("/RefreshToken")
    public ResponseEntity<JWTAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }


}
