package com.example.ddashmanagement.Entites;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.Instant;

public class Publication {
    @DBRef
    private Product product ;
    @DBRef
    private Enchere enchere ;

    @DBRef
    private Administrateur administrateur;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
}
