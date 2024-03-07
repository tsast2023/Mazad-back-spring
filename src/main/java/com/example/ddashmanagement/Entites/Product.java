package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.StatusProduct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
@Document
@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @Size(min = 0, max = 10, message = "La taille du champ doit être comprise entre 0 et 10 caractères.")
    private String id ;
    @NotBlank
    @Size(min = 0, max = 25, message = "La taille du champ doit être comprise entre 0 et 25 caractères.")
    private String libelleProduct ;
    private List<String> Galerie ;
    private Integer stockInitiale ;
    private Integer stockactuel ;
    private Integer prixPrincipale ;
    private List<String> couleurs ;
    private String CodeABarre ;
    private String QrCode ;
    private String Fournisseur ;

    private String visiteMagasin ;
    private Integer prixMazedOnline ;
    private StatusProduct status;
    @DBRef
    private CategoryFille category ;
    @DBRef
    private CategoryFille categoryFille;

    private String Description ;
    @DBRef
    private Enchere enchere ;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
