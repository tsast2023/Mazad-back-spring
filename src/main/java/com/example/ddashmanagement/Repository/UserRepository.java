package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Ennum.RoleUser;
import com.example.ddashmanagement.Entites.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ $or: [ { 'pseudo': ?0 }, { 'numTel': ?1 } ] }")
    Optional<User> findByPseudoOrNumtel(String pseudo, String numTel);
    User findByRole(RoleUser role);
}
