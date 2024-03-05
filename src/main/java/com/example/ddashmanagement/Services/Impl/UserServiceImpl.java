package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Repository.UserRepository;
import com.example.ddashmanagement.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByPseudoOrNumtel(username , username).orElseThrow(() -> new UsernameNotFoundException("User Not Found "));
            }
        };
    }


}
