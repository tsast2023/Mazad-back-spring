package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.StatusVendeur;
import com.example.ddashmanagement.Ennum.TypeVendeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@TypeAlias("Vendeur")
@Data
public class Vendeur extends User{
    private String imgCin;
    private Integer cin ;
    private String patente ;
    private StatusVendeur status = StatusVendeur.BLOQUER ;
    private TypeVendeur type ;
    private String nomSociete ;
    private Date dateNaissance ;
    private String photoDeProfil ;
    private String SocketId ;
    @DBRef
    private Solde solde ;

}
