package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Dto.SignUpRequest;
import com.example.ddashmanagement.Ennum.RoleUser;
import com.example.ddashmanagement.Ennum.StatusUser;
import com.example.ddashmanagement.Ennum.StatusVendeur;
import com.example.ddashmanagement.Entites.*;
import com.example.ddashmanagement.Repository.UserRepository;
import com.example.ddashmanagement.Repository.VendeurRepository;
import com.example.ddashmanagement.Services.IServiceDemande;
import com.example.ddashmanagement.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService  {
    private final UserRepository userRepository;
    private final IServiceDemande iServiceDemande ;
    private final VendeurRepository vendeurRepository ;
    private final EmailService emailService ;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
              return userRepository.findByPseudoOrNumtel(username , username).orElseThrow(() -> new UsernameNotFoundException("User Not Found "));
              /* if(user.getStatus().equals(StatusUser.BLOQUER)){
                   throw new UsernameNotFoundException("User is blocked and cannot log in");
            }
               return user ;*/
            }
        };
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User Bloquer(String id) {
        User u = userRepository.findById(id).get();
        u.setStatus(StatusUser.BLOQUER);
        return userRepository.save(u);

    }

    @Override
    public User debloquer(String id) {
        User u = userRepository.findById(id).get();
        u.setStatus(StatusUser.DEBLOQUER);
        return userRepository.save(u);
    }

    @Override
    public List<User> findParticipant(Optional<String> pseudo, Optional<String> numtel, Optional<String> nom, Optional<String> prenom) {
        return userRepository.findBypseudoOrNumTelOrNomOrPrenom(pseudo.orElse(null) , nom.orElse(null) , prenom.orElse(null), numtel.orElse(null));
    }

    @Override
    public User AjouterSolde(Integer solde, String id) {
        User u = userRepository.findById(id).get();
        Solde s = u.getSolde();
        s.setSoldeMazed(s.getSoldeMazed() + solde);
        u.setSolde(s);
        return userRepository.save(u);
    }

    @Override
    public User Createvendeur(SignUpRequest signUpRequest) {
        Vendeur vendeur = new Vendeur();
        vendeur.setEmail(signUpRequest.getEmail());
        vendeur.setPrenom(signUpRequest.getPrenom());
        vendeur.setNomFamille(signUpRequest.getNomFamille());
        vendeur.setPseudo(signUpRequest.getPseudo());
        vendeur.setRole(RoleUser.Vendeur);
        vendeur.setCin(signUpRequest.getCin());
        vendeur.setPatente(signUpRequest.getPatente());
        vendeur.setImgCin(signUpRequest.getImgCin());
        vendeur.setNomSociete(signUpRequest.getNomSociete());
        vendeur.setType(signUpRequest.getType());
        vendeur.setNumTel(signUpRequest.getNumTel());
        vendeur.setStatus(StatusUser.BLOQUER);
        return userRepository.save(vendeur);
    }

    @Override
    public String AcceptationVendeur(String demandeId) {
        DemandesSuperAdmin demandeAcceptaion = iServiceDemande.findById(demandeId);
        Optional<User> vendeurOptional = userRepository.findById(demandeAcceptaion.getVendeurId());
        if(vendeurOptional.isPresent()){
            Vendeur v = (Vendeur) vendeurOptional.get();
            v.setStatusVendeur(StatusVendeur.ACCEPTER);
            userRepository.save(v);
            emailService.sendEmail(v.getEmail(), "" , "");
            return "email de validation de compte envoyer avec succées";
        }
        else {
            return "email non envoyer" ;
        }

    }


}
