package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.Category;
import com.example.ddashmanagement.Entites.CategoryFille;

import java.util.List;


public interface IServiceCategory {
    public CategoryFille createCategorie(CategoryFille c);

    public CategoryFille findCategorieById(String id);
    public CategoryFille updateCategorie(Category c);
    public List<CategoryFille> findAllCategorie();
    public void deleteCategorie(Category c);
    public boolean categorieExists(String id);
}
