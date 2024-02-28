package com.example.ddashmanagement.Entites;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    private String id ;
    @NotBlank
    @Size(min = 0, max = 25, message = "La taille du champ doit être comprise entre 0 et 25 caractères.")
    private String libeléCategorie ;
    private Number NombreDesProduits ;
    private Map<String , String> critere ;
    @DBRef
    private Collection<Product> products = new ArrayList<>() ;
    @DBRef
    private Collection<CategoryFille> CategoryFille = new ArrayList<>();

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
