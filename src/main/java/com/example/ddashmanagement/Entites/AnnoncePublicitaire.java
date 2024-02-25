package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.StatusAnnonce;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnoncePublicitaire {
    private String id ;
    private List<String> Contenu ;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    private Date DatePublication ;

    private StatusAnnonce status ;

    @DBRef
    private Product product ;




}
