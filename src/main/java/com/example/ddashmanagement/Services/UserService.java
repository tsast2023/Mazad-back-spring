package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService {
    UserDetailsService userDetailsService();
    User findById(String id) ;

}
