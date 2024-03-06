package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.Category;
import com.example.ddashmanagement.Entites.CategoryFille;

import java.util.List;
import java.util.Optional;


public interface IServiceCategory {
    public CategoryFille createCategorie(CategoryFille c);

    public CategoryFille findCategorieById(String id);
    public CategoryFille updateCategorie(String id ,CategoryFille c);
    public List<CategoryFille> findAllCategorie();
    public void deleteCategorie(CategoryFille c);
    public boolean categorieExists(String id);

    public void ChangerStatus(CategoryFille c);
    List<CategoryFille> findCategories(Optional<EtatCategory> etat, Optional<StatusCategorie> status, Optional<TypeCategory> type);
}
