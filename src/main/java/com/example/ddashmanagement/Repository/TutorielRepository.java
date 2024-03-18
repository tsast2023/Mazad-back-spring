package com.example.ddashmanagement.Repository;

import com.example.ddashmanagement.Entites.Tutoriel;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TutorielRepository  extends MongoRepository<Tutoriel , String> {
}
