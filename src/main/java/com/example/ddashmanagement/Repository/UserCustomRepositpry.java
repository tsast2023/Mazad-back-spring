package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Ennum.StatusEnchere;
import com.example.ddashmanagement.Entites.CategoryFille;

import com.example.ddashmanagement.Entites.User;

import java.util.List;

public interface UserCustomRepositpry {

    List<User> findBypseudoOrNumTelOrNomOrPrenom(String pseudo, String nom , String prenom , String numtel);
}
