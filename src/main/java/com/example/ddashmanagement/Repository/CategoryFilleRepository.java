package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.CategoryFille;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryFilleRepository extends MongoRepository<CategoryFille , String> {
}
