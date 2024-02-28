package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document(collection = "Acheteur-vendeur")
@NoArgsConstructor
@AllArgsConstructor
public class Acheteur_Vendeur  extends utilisateurs{
    @Indexed(unique = true)
    private String Pseudo ;
    private Date DateNaissance ;
    private String photoDeProfil ;
    private String Patente ;
    private String SocketId ;
    @DBRef
    private Solde solde ;
}
