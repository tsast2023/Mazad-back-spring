package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@Document
@TypeAlias("categorieFille")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFille{
    @Id
    private String id ;
    @NotBlank
    @Size(min = 0, max = 25, message = "La taille du champ doit être comprise entre 0 et 25 caractères.")
    private String libeléCategorie ;
    private TypeCategory type ;
    private Number NombreDesProduits ;
    private StatusCategorie status ;
    private EtatCategory etat ;
    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
    @DBRef
    private Collection<Product> products = new ArrayList<>() ;
    @DBRef
    private Collection<Enchere> Enchere = new ArrayList<>() ;
    @DBRef
    private Collection<Category> categories = new ArrayList<>() ;
    private Map<String , String> critere ;


}
