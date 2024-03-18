package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Dto.JWTAuthenticationResponse;
import com.example.ddashmanagement.Dto.RefreshTokenRequest;
import com.example.ddashmanagement.Dto.SignInRequest;
import com.example.ddashmanagement.Dto.SignUpRequest;
import com.example.ddashmanagement.Ennum.RoleUser;
import com.example.ddashmanagement.Ennum.StatusUser;
import com.example.ddashmanagement.Ennum.StatusVendeur;
import com.example.ddashmanagement.Entites.Acheteur;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Entites.Vendeur;
import com.example.ddashmanagement.Repository.UserRepository;
import com.example.ddashmanagement.Services.AuthenticationService;
import com.example.ddashmanagement.Services.IServiceDemande;
import com.example.ddashmanagement.Services.JWTServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager ;
    private final JWTServices jwtServices ;
    private final IServiceDemande iServiceDemande ;

    public User SignUp(SignUpRequest signUpRequest) {
        Vendeur vendeur = new Vendeur();
        Acheteur Acheteur = new Acheteur();
        if (signUpRequest.getRole().name().equals("Acheteur")) {
            Acheteur.setEmail(signUpRequest.getEmail());
            Acheteur.setPrenom(signUpRequest.getPrenom());
            Acheteur.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
           // Acheteur.setDateNaissance(signUpRequest.getDateNaissance());
            Acheteur.setPhotoDeProfil(signUpRequest.getPhotoDeProfil());
          //  Acheteur.setNomFamille(signUpRequest.getNomFamille());
            Acheteur.setPseudo(signUpRequest.getPseudo());
            Acheteur.setRole(RoleUser.Acheteur);
            Acheteur.setNumTel(signUpRequest.getNumTel());
            userRepository.save(Acheteur);
            System.out.println(Acheteur);

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
            vendeur.setStatusVendeur(StatusVendeur.ENATTENTE);
            userRepository.save(vendeur);
            iServiceDemande.DemandeAcceptationVendeur(vendeur.getId());
              /* NotificationMessage notificationMessage = new NotificationMessage();
                notificationMessage.setTitle("Demande de Modification");
                notificationMessage.setBody("Une demande de Modification a été effectuée pour le produit " + ProductId);
                notificationMessage.setRecipentToken("TOKEN_DU_SUPER_ADMIN"); // Remplacez TOKEN_DU_SUPER_ADMIN par le token du dispositif du super admin

                // Envoyer la notification au super admin
                String result = firebaseMessagingService.SendNotificationByToken(notificationMessage);

                return result;*/
            return vendeur;


        }
        else {
            throw new IllegalArgumentException("Role Not Found");
        }
    }
    public JWTAuthenticationResponse SignIn(SignInRequest signInRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getLogin() , signInRequest.getPassword()));
        System.out.print("hello hello");
        var user = userRepository.findByPseudoOrNumtel(signInRequest.getLogin() , signInRequest.getLogin()).orElseThrow(() -> new IllegalArgumentException("Invalid Email or password"));
         System.out.print(user);
        var jwt = jwtServices.generateToken(user);
        var refreshToken = jwtServices.generateRefreshToken(new HashMap<>() , user);
        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshtoken(refreshToken);

        return jwtAuthenticationResponse;

    }

    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtServices.ExtractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByPseudoOrNumtel(userEmail , userEmail).orElseThrow();
        if(jwtServices.isTokenValid(refreshTokenRequest.getToken() , user)){
            var jwt = jwtServices.generateToken(user);
            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshtoken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;


        }
return null ;
        }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }
}
