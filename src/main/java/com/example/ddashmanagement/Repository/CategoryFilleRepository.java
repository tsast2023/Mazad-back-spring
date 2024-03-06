package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
import com.example.ddashmanagement.Entites.CategoryFille;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CategoryFilleRepository extends MongoRepository<CategoryFille , String> {
    @Query("{ $or: [ { 'etat': ?0 }, { 'status': ?1 } , { 'type': ?2 }] }")
    List<CategoryFille> findCategoryFilleByEtatOrStatusOrType(StatusCategorie status , TypeCategory type , EtatCategory etat);

}
