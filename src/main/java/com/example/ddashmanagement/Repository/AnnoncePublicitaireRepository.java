package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.AnnoncePublicitaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AnnoncePublicitaireRepository extends MongoRepository<AnnoncePublicitaire , String> {
}
