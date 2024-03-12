package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Ennum.StatusEnchere;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.Enchere;

import java.util.List;

public interface EnchereRepositoryCustom {
    List<Enchere> findByStatusOrCategory(StatusEnchere status, CategoryFille category);
}
