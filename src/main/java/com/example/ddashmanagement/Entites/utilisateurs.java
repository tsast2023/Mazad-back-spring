package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.RoleUser;
import jakarta.validation.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.Instant;

public class utilisateurs {
    private String nomFamille ;
    private String Prenom ;
    private String NumTel ;
    @Email
    private String email ;
    private String MotdePasse ;

    private RoleUser role ;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;


}
