package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Ennum.StatusEnchere;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.Enchere;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EnchereCustomRepositoryImpl implements  EnchereRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    public EnchereCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Enchere> findByStatusOrCategory(StatusEnchere status, CategoryFille category) {
        Query query = new Query();
        if (status != null) {
            query.addCriteria(Criteria.where("status").is(status));
        }
        if (category != null) {
            query.addCriteria(Criteria.where("category").is(category));
        }


        return mongoTemplate.find(query, Enchere.class);
    }
}
