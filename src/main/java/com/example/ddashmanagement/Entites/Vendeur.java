package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.StatusUser;
import com.example.ddashmanagement.Ennum.TypeVendeur;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

import java.util.Date;
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


}
