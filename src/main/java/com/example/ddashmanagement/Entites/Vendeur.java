package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.StatusUser;
import com.example.ddashmanagement.Ennum.TypeVendeur;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

@TypeAlias("Vendeur")
@Data
public class Vendeur extends User{
    private String imgCin;
    private Integer cin ;
    private String patente ;
    private TypeVendeur type ;
    private String nomSociete ;
    private Date dateNaissance ;
    private String photoDeProfil ;
    private String SocketId ;

    @DBRef
    private List<Ventes> ventes;


}
