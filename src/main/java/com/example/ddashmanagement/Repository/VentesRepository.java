package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.Ventes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface VentesRepository extends MongoRepository<Ventes, String> {
    List<Ventes> findTop20ByOrderByAvis_EvaluationDescCreatedAtDesc(int evaluation);
}
