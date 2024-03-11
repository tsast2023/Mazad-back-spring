package com.example.ddashmanagement.Dto;

import com.example.ddashmanagement.Ennum.UniteFacilite;
import com.example.ddashmanagement.Entites.Product;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EnchereModifiable {
    private Integer CoutClic ;
    private Integer CoutParticipation ;
    private List<Integer> ValeurMajoration ;
    private Integer Facilité ;
    private Boolean Remboursement ;
    private Integer ValeurRemboursement ;
    private Date datedeclenchement ;
    private Date datefermeture ;
    private UniteFacilite unité ;
    private Integer NombreParticipant ;
    private  Integer NombreMois ;
    private String ville ;
    private Integer prixMagasin ;
    private Integer prixMazedOnline ;
    private Product products ;
}
