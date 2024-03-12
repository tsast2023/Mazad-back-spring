package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Dto.ConfigPredéfiniesEnchere;
import com.example.ddashmanagement.Dto.EnchereRequest;
import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.StatusEnchere;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.Enchere;
import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Entites.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IEnchereService {
    public Enchere CreateEnchereBrouillon(EnchereRequest e) ;

    public Enchere publishBidNow(ConfigPredéfiniesEnchere enchere , String IdEnchere);

    public Enchere saveBidBrouillon(ConfigPredéfiniesEnchere enchere , String IdEnchere);

    public Enchere findBidById(String id);
    public Enchere scheduleBidPublication(ConfigPredéfiniesEnchere enchere, LocalDateTime publicationDate , String IdEnchere);

    public List<Enchere> getAll();

    public String demandeAnnulationEnchere(String enchereId , User user);

    public String deleteEnchere(String id);

    public String demandeModificationEnchere(String enchereId , User user , Enchere enchere);

    public String annulerEnchereEtInformerParticipants(String enchereId, User requester);


    public String validerDemandeModification(String demandeId) ;

    List<Enchere> findEnchere(Optional<CategoryFille> category, Optional<StatusEnchere> status);

    public Enchere epinglerEnchere(String id);

    public Enchere desepinglerEnchere(String  id  , Integer nombreMois);



}
