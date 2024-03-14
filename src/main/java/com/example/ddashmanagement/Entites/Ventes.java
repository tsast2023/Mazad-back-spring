package com.example.ddashmanagement.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ventes {
    @Id
    private String id ;

    @DBRef
    private Product product ;

    @DBRef
    private Vendeur vendeur ;

    @DBRef
    private Acheteur acheteur;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
}
