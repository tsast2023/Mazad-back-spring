package com.example.ddashmanagement.Dto;

import com.example.ddashmanagement.Ennum.StatusEnchere;
import com.example.ddashmanagement.Entites.Product;
import lombok.Data;

import java.util.List;

@Data
public class EnchereRequest {
    private String ville ;
    private Integer prixMagasin ;
    private Integer prixMazedOnline ;

    private Product products ;
    private StatusEnchere status = StatusEnchere.Brouillon ;
}
