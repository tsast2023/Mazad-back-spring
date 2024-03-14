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
    private Integer soldeMazed ;
    private Integer soldeAquisition ;
    private User user ;
    private recharge recharge ;


}
