package com.example.ddashmanagement.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Administrateur")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Administrateur  extends utilisateurs{
    @Id
    private String id ;

}
