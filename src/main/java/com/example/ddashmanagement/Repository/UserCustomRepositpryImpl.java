package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCustomRepositpryImpl  implements  UserCustomRepositpry{
    private final MongoTemplate mongoTemplate;

    public UserCustomRepositpryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> findBypseudoOrNumTelOrNomOrPrenom(String pseudo, String nom, String prenom, String numTel) {
        Query query = new Query();
        if (pseudo != null) {
            query.addCriteria(Criteria.where("pseudo").is(pseudo));
        }
        if (nom != null) {
            query.addCriteria(Criteria.where("nom").is(nom));
        }
        if (prenom != null) {
            query.addCriteria(Criteria.where("prenom").is(prenom));
        }
        if (numTel != null) {
            query.addCriteria(Criteria.where("numTel").is(numTel));
        }

        return mongoTemplate.find(query, User.class);
    }


}

