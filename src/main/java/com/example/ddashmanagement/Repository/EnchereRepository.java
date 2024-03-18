package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Ennum.StatusEnchere;
import com.example.ddashmanagement.Entites.Enchere;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface EnchereRepository extends MongoRepository<Enchere , String > , EnchereRepositoryCustom {
    List<Enchere> findTop20ByStatusOrderByDatedeclenchementAsc(StatusEnchere status, Pageable pageable);
    List<Enchere> findByCoutParticipationOrCoutClic(Integer coutParticipation, Integer coutClic);

    List<Enchere> findByCategory_Id(String categoryId);
    List<Enchere> findTop20ByStatusAndCategory_IdOrderByCreatedAtDesc(StatusEnchere status, String categoryId, Pageable pageable);
}

