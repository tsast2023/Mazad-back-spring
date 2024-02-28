package com.example.ddashmanagement.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteRecharge {
    @Id
    private String Id ;
    private String NumSérie ;
    private BigDecimal valeur ;

}
