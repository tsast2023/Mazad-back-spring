package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Services.IServiceProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private  final IServiceProduct iServiceProduct ;

    public ProductController(IServiceProduct iServiceProduct) {
        this.iServiceProduct = iServiceProduct;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product p , BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Le libelé de la catégorie ne doit pas être vide");
        }
         iServiceProduct.createProduct(p);
        return ResponseEntity.ok("Catégorie enregistrée avec succès");
    }

}
