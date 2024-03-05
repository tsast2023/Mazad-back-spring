package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.StatusVendeur;
import com.example.ddashmanagement.Ennum.TypeVendeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document(collection = "vendeur")
@NoArgsConstructor
@AllArgsConstructor
public class Vendeur extends User{
    private String ImgCin;
    private Integer Cin ;
    private String Patente ;
    private StatusVendeur Status;
    private TypeVendeur type ;
    private String NomSociete ;
    private Date DateNaissance ;
    private String photoDeProfil ;
    private String SocketId ;
    @DBRef
    private Solde solde ;

}
