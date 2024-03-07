package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.DemandesSuperAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DemandeRepository extends MongoRepository<DemandesSuperAdmin , String>
{
}
