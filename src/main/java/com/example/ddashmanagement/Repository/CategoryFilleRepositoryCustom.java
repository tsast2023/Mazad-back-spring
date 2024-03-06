package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.CategoryFille;

import java.util.List;

public interface CategoryFilleRepositoryCustom {
    List<CategoryFille> findByCriteria(EtatCategory etat, TypeCategory type, StatusCategorie status);
}

