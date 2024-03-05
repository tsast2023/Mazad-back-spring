package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Dto.SignUpRequest;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService ;

    @PostMapping("/SignUp")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
       return ResponseEntity.ok(authenticationService.SignUp(signUpRequest));
    }

}
