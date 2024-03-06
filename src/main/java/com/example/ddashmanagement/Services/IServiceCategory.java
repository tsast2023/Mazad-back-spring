package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.Category;
import com.example.ddashmanagement.Entites.CategoryFille;

import java.util.List;


public interface IServiceCategory {
    public CategoryFille createCategorie(CategoryFille c);

    public CategoryFille findCategorieById(String id);
    public CategoryFille updateCategorie(String id ,CategoryFille c);
    public List<CategoryFille> findAllCategorie();
    public void deleteCategorie(CategoryFille c);
    public boolean categorieExists(String id);

    public void ChangerStatus(CategoryFille c);
    public List<CategoryFille> Filtre(StatusCategorie status , TypeCategory type , EtatCategory etat);
}
