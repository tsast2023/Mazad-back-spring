package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.OffreEnchere;
import com.example.ddashmanagement.Ennum.StatusEnchere;
import com.example.ddashmanagement.Ennum.UniteFacilite;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Enchere {
    @Id
    private String id ;
    private Product product ;
    private Integer CoutClic ;
    private Integer CoutParticipation ;
    private List<Integer> ValeurMajoration ;
    private Integer Facilité ;
    private Boolean Remboursement ;
    private Integer ValeurRemboursement ;
    private Date datedeclenchement ;
    private Date datefermeture ;
    private String ville ;
    private Integer PrixMagasin ;
    private Integer PrixMazedOnline ;
    private Integer NombreParticipant ;
    private Integer NombreParticipantréel ;
    private Integer ExtensionTime ;
    private  Integer NombreMois ;
    private Integer prixMazedOnlineFinal ;
    @DBRef
    private Collection<User> participants = new ArrayList<>();

    private List<enchérissement> enchérissements ;

    private UniteFacilite unité ;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private Date DatePublication ;

    private StatusEnchere Status ;








}
