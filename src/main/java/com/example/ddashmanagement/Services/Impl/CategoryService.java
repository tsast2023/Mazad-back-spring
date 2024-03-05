package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.Category;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Repository.CategoryFilleRepository;
import com.example.ddashmanagement.Repository.CategoryRepository;
import com.example.ddashmanagement.Services.IServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements IServiceCategory {
    @Autowired
    CategoryFilleRepository categoryFilleRepository;


    @Override
    public CategoryFille createCategorie(CategoryFille c) {
        if(c.getCategories().isEmpty()){
            c.setType(TypeCategory.CATEGORYPARENTE);
        }
        else{
            c.setType(TypeCategory.CATEGORYFILLE);
        }
      return categoryFilleRepository.save(c);

    }

    @Override
    public CategoryFille findCategorieById(String id) {

        return categoryFilleRepository.findById(id).get();
    }

    @Override
    public Category updateCategorie(Category c) {
        return null;
    }

    @Override
    public List<CategoryFille> findAllCategorie() {
        return categoryFilleRepository.findAll();
    }

    @Override
    public void deleteCategorie(Category c) {
        categoryFilleRepository.delete(c);

    }

    @Override
    public boolean categorieExists(String id) {
        return categoryFilleRepository.existsById(id);
    }
}
