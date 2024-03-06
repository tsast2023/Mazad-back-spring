package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Ennum.EtatCategory;
import com.example.ddashmanagement.Ennum.StatusCategorie;
import com.example.ddashmanagement.Ennum.TypeCategory;
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
    @GetMapping("/filtreCategory")
    public List<CategoryFille> filter(@RequestBody StatusCategorie status ,@RequestBody TypeCategory type ,@RequestBody EtatCategory etat){
        return iServiceCategory.Filtre(status , type , etat);
    }
    @PutMapping("/updateCategory")
    public CategoryFille update(String id , CategoryFille c) {
        return iServiceCategory.updateCategorie(id , c);
    }

}
