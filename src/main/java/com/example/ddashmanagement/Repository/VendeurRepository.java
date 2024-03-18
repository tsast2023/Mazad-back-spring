package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.Vendeur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VendeurRepository extends MongoRepository<Vendeur , String> {
}
