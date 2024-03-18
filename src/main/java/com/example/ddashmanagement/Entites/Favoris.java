package com.example.ddashmanagement.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Favoris {
    @Id
    private String id ;
    @DBRef
    private Collection<Product> listProduct = new ArrayList<>();
    @DBRef
    private Collection<Enchere> listEnchere = new ArrayList<>() ;


}
