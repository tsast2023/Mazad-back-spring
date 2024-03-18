package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository  extends MongoRepository<Product , String> {

    List<Product> findByTypeProductTrue();

    List<Product> findTop20ByOrderByPublicationDateAsc(Pageable pageable);

    List<Product> findTop20ByPromotionTrueOrderByCreatedAtDesc(Pageable pageable);

}
