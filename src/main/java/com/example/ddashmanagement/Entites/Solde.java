package com.example.ddashmanagement.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Solde {
    private String id ;
    private BigDecimal soldeMazed ;
    private BigDecimal soldeAquisition ;

}
