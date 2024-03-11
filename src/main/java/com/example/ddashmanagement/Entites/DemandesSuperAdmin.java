package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.TypedeDemande;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
public class DemandesSuperAdmin {
    @Id
    private String Id ;
    private TypedeDemande typeDemande ;

    private String categoryId ;

    private String adminId ;

    private String productId ;

    private Product product ;
    private Enchere enchere ;

    private String enchereId;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;


}
