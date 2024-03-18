package com.example.ddashmanagement.Controller;

import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Entites.User;
import com.example.ddashmanagement.Services.IServiceProduct;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private  final IServiceProduct iServiceProduct ;

    public ProductController(IServiceProduct iServiceProduct) {
        this.iServiceProduct = iServiceProduct;
    }

    @PostMapping("/publishNow")
    public ResponseEntity<Product> publishProductNow(@RequestBody Product product) {
        Product publishedProduct = iServiceProduct.publishProductNow(product);
        return ResponseEntity.ok(publishedProduct);
    }

    // Planifier la publication d'un produit
    @PostMapping("/schedulePublication")
    public ResponseEntity<Product> scheduleProductPublication(@RequestBody Product product, @RequestParam("publicationDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime publicationDateTime) {
        Product scheduledProduct = iServiceProduct.scheduleProductPublication(product, publicationDateTime);
        return ResponseEntity.ok(scheduledProduct);
    }

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return iServiceProduct.findAllProduct() ;
    }
    @GetMapping("/productDetail")
    public Product ProductDetail(@RequestParam String id) {
        return iServiceProduct.findProductById(id);
    }

    @PostMapping("/demandesModicationProduct")
    public String demandeModificationProduct(@RequestParam String id , @AuthenticationPrincipal User userDetails ,@RequestBody Product p){
        return iServiceProduct.DemandeupdateProduct(id , userDetails , p);

    }
    @PostMapping("/delete")
    public String demandeSuppression (@RequestParam String id  , @AuthenticationPrincipal User userDetails){
       return iServiceProduct.deleteProduct(id , userDetails);
    }
    /************* mobile*****************************/
    @GetMapping("/AlaUne")
    public List<Product> getProductsOfTypeProductTrue() {
        return iServiceProduct.getProductsOfTypeProductTrue();
    }

    @GetMapping("/top20")
    public List<Product> getFirst20ProductsOrderByPublicationDate() {
        return iServiceProduct.getFirst20ProductsOrderByPublicationDate();
    }

    @GetMapping("/promotions")
    public List<Product> getLatestPromotionProducts() {
        return iServiceProduct.getLatestPromotionProducts();
    }
}
