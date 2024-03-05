package com.example.ddashmanagement.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@TypeAlias("Acheteur")
@Data
public class Acheteur extends User {
    private Date dateNaissance ;
    private String photoDeProfil ;
    private String SocketId ;
    @DBRef
    private Solde solde ;
}
