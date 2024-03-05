package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RestController;

@RestResource
public interface RoleRepository extends MongoRepository<Role , String> {
}
