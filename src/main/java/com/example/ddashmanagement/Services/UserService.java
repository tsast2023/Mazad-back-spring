package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Dto.SignUpRequest;
import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserDetailsService userDetailsService();
    User findById(String id) ;

    List<User> getAll();

    User Bloquer(String id) ;

    User debloquer(String id);

    List<User> findParticipant(Optional<String> pseudo, Optional<String> numTel, Optional<String> nom ,Optional<String> prenom);


    User AjouterSolde(Integer solde , String id);

    User Createvendeur(SignUpRequest signUpRequest);


    String AcceptationVendeur(String demandeId);


}
