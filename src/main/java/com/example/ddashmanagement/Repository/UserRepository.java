package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Ennum.RoleUser;
import com.example.ddashmanagement.Entites.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    User findByRole(RoleUser role);
}
