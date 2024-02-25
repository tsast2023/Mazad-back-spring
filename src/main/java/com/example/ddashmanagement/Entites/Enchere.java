package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.StatusEnchere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Enchere {
    @Id
    private String id ;
    @DBRef
    private Product product ;
    private Integer CoutClic ;
    private Integer CoutParticipation ;
    private Integer ValeurMajoration ;
    private Integer Facilité ;
    private Boolean Remboursement ;
    private Integer ValeurRemboursement ;
    private Date datedeclenchement ;
    private Date datefermeture ;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private Date DatePublication ;

    private StatusEnchere Status ;






}
