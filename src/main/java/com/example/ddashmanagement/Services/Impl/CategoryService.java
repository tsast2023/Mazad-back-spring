package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Repository.CategoryFilleRepository;
import com.example.ddashmanagement.Services.IServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IServiceCategory {
    @Autowired
    CategoryFilleRepository categoryFilleRepository;


    @Override
    public CategoryFille createCategorie(CategoryFille c) {
        System.out.println("CategoryFille" + c);
        CategoryFille category = new CategoryFille();
        if(c.getCategories().isEmpty()){
            category.setLibeléCategorie(c.getLibeléCategorie());
            category.setType(TypeCategory.CATEGORYPARENTE);

        }
        else{
            category.setCategories(c.getCategories());
            category.setLibeléCategorie(c.getLibeléCategorie());
            category.setType(TypeCategory.CATEGORYFILLE);
        }
      return categoryFilleRepository.save(category);

    }

    @Override
    public CategoryFille findCategorieById(String id) {

        return categoryFilleRepository.findById(id).get();
    }

    @Override
    public CategoryFille updateCategorie(String id, CategoryFille c) {

        CategoryFille cat = categoryFilleRepository.findById(id).orElseThrow(() -> new RuntimeException("Entité non trouvée avec id " + id));

        if (cat.getProducts().isEmpty()) {
            cat.setLibeléCategorie(c.getLibeléCategorie());
            cat.setCritere(c.getCritere());
        }

        return categoryFilleRepository.save(cat);
    }


    @Override
    public List<CategoryFille> findAllCategorie() {
        return categoryFilleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public void deleteCategorie(CategoryFille c) {
        if (c.getProducts().isEmpty()){
            categoryFilleRepository.delete(c);
        }
    }

    @Override
    public boolean categorieExists(String id) {
        return categoryFilleRepository.existsById(id);
    }

    @Override
    public void ChangerStatus(CategoryFille c) {

    }

    @Override
    public List<CategoryFille> findCategories
            (Optional<EtatCategory> etat,
             Optional<StatusCategorie> status,
             Optional<TypeCategory> type) {
        return categoryFilleRepository.findByCriteria(etat.orElse(null), type.orElse(null), status.orElse(null));
    }





}
