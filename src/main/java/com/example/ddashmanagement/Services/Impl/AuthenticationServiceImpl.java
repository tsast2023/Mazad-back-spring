package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Dto.JWTAuthenticationResponse;
import com.example.ddashmanagement.Dto.RefreshTokenRequest;
import com.example.ddashmanagement.Dto.SignInRequest;
import com.example.ddashmanagement.Dto.SignUpRequest;
import com.example.ddashmanagement.Ennum.RoleUser;
import com.example.ddashmanagement.Entites.Acheteur;
import com.example.ddashmanagement.Entites.Role;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Entites.Vendeur;
import com.example.ddashmanagement.Repository.AcheteurRepository;
import com.example.ddashmanagement.Repository.UserRepository;
import com.example.ddashmanagement.Repository.VendeurRepository;
import com.example.ddashmanagement.Services.AuthenticationService;
import com.example.ddashmanagement.Services.JWTServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AcheteurRepository acheteurRepository;
    private final VendeurRepository vendeurRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager ;
    private final JWTServices jwtServices ;

    public User SignUp(SignUpRequest signUpRequest) {
        User user = new User();
        Vendeur vendeur = new Vendeur();
        Acheteur Acheteur = new Acheteur();
        if (signUpRequest.getRole().name().equals("Acheteur")) {
            Acheteur.setEmail(signUpRequest.getEmail());
            Acheteur.setPrenom(signUpRequest.getPrenom());
            Acheteur.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            Acheteur.setDateNaissance(signUpRequest.getDateNaissance());
            Acheteur.setPhotoDeProfil(signUpRequest.getPhotoDeProfil());
            Acheteur.setNomFamille(signUpRequest.getNomFamille());
            Acheteur.setPseudo(signUpRequest.getPseudo());
            Acheteur.setRole(RoleUser.Acheteur);
            Acheteur.setNumTel(signUpRequest.getNumTel());
            userRepository.save(Acheteur);
           return  Acheteur ;

        }

        else if (signUpRequest.getRole().name().equals("Vendeur")) {
            vendeur.setEmail(signUpRequest.getEmail());
            vendeur.setPrenom(signUpRequest.getPrenom());
            vendeur.setDateNaissance(signUpRequest.getDateNaissance());
            vendeur.setPhotoDeProfil(signUpRequest.getPhotoDeProfil());
            vendeur.setNomFamille(signUpRequest.getNomFamille());
            vendeur.setPseudo(signUpRequest.getPseudo());
            vendeur.setRole(RoleUser.Vendeur);
            vendeur.setCin(signUpRequest.getCin());
            vendeur.setPatente(signUpRequest.getPatente());
            vendeur.setImgCin(signUpRequest.getImgCin());
            vendeur.setNomSociete(signUpRequest.getNomSociete());
            vendeur.setType(signUpRequest.getType());
            vendeur.setNumTel(signUpRequest.getNumTel());
            userRepository.save(vendeur);
            return vendeur;


        }
        else {
            throw new IllegalArgumentException("Role Not Found");
        }
    }
    public JWTAuthenticationResponse SignIn(SignInRequest signInRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail() , signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid Email or password"));
        var jwt = jwtServices.generateToken(user);
        var refreshToken = jwtServices.generateRefreshToken(new HashMap<>() , user);
        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshtoken(refreshToken);

        return jwtAuthenticationResponse;

    }

    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtServices.ExtractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtServices.isTokenValid(refreshTokenRequest.getToken() , user)){
            var jwt = jwtServices.generateToken(user);
            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshtoken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;


        }
return null ;
        }
}
