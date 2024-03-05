package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Entites.Category;
import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Services.IServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    IServiceCategory iServiceCategory;

    @GetMapping("/getAll")
    public List<CategoryFille> getAll(){
       return iServiceCategory.findAllCategorie() ;
        }
    @PostMapping("/addCategory")
    public CategoryFille addCategorie(@RequestBody CategoryFille  categorie){
        return iServiceCategory.createCategorie(categorie);
    }

}
