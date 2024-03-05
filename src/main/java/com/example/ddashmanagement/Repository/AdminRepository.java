package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.Administrateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdminRepository extends MongoRepository<Administrateur , String> {
}
