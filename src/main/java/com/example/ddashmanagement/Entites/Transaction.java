package com.example.ddashmanagement.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    private String Id ;
    @DBRef
    private Solde solde;
    @DBRef
    private User user ;
    @DBRef
    private Administrateur administrateur ;


}
