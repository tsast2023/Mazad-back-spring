package com.example.ddashmanagement.Dto;

import com.example.ddashmanagement.Ennum.RoleUser;
import com.example.ddashmanagement.Ennum.StatusVendeur;
import com.example.ddashmanagement.Ennum.TypeVendeur;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Data
public class SignUpRequest {
    private String nomFamille ;
    private String Prenom ;
    private String NumTel ;
    private String email ;
    private String password ;
    private String pseudo ;
    private Date DateNaissance ;
    private String photoDeProfil ;
    private String ImgCin;
    private Integer Cin ;
    private String Patente ;
    private StatusVendeur Status ;
    private TypeVendeur type ;
    private String NomSociete ;
    private RoleUser role ;
}
