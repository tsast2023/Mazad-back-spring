package com.example.ddashmanagement.Entites;

import com.example.ddashmanagement.Ennum.TypeCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Data
@TypeAlias("CategorieParente")
public class Category extends CategoryFille{

    @DBRef
    private Collection<CategoryFille> CategoryFille = new ArrayList<>();


}
