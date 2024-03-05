package com.example.ddashmanagement.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document(collection = "Acheteur")
@NoArgsConstructor
@AllArgsConstructor
public class Acheteur extends User {
    private Date DateNaissance ;
    private String photoDeProfil ;
    private String SocketId ;
    @DBRef
    private Solde solde ;
}
