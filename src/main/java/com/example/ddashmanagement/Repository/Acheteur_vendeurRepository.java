package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.Acheteur_Vendeur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Acheteur_vendeurRepository extends MongoRepository<Acheteur_Vendeur , String> {
}
