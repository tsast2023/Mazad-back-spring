package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.Acheteur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AcheteurRepository extends MongoRepository<Acheteur , String > {
}
