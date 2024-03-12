package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.Enchere;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EnchereRepository extends MongoRepository<Enchere , String > , EnchereRepositoryCustom {
}
