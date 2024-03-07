package com.example.ddashmanagement.Entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Collection;

@TypeAlias("Administrateur")
public class Administrateur  extends User {
    @DBRef
    private Collection<DemandesSuperAdmin> demandes = new ArrayList<>() ;

}
