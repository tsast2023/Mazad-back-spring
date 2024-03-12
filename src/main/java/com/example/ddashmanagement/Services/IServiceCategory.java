package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Optional;


public interface IServiceCategory {
    public CategoryFille createCategorie(CategoryFille c);

    public CategoryFille findCategorieById(String id);
    public CategoryFille updateCategorie(String id ,CategoryFille c);
    public List<CategoryFille> findAllCategorie();
    public void deleteCategorie(CategoryFille c);


    String ChangerStatusDemande(String idCategory , User userDetails);
    List<CategoryFille> findCategories(Optional<EtatCategory> etat, Optional<StatusCategorie> status, Optional<TypeCategory> type);

    public String validerChangementStatusCategorie(String idDemande);
}

