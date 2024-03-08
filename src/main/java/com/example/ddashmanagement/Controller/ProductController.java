package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Services.IServiceProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return ResponseEntity.badRequest().body("Le libelé de produit ne doit pas être vide");
        }
         iServiceProduct.createProduct(p);
        return ResponseEntity.ok("produit enregistrée avec succès" + p);
    }

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return iServiceProduct.findAllProduct() ;
    }
    @GetMapping("/productDetail")
    public Product ProductDetail(@RequestParam String id) {
        return iServiceProduct.findProductById(id);
    }

}
