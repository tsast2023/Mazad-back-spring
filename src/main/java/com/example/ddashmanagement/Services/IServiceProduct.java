package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Entites.User;

import java.time.LocalDateTime;
import java.util.List;

public interface IServiceProduct {

    public Product publishProductNow(Product product);

    public Product scheduleProductPublication(Product product, LocalDateTime publicationDate);

    public Product findProductById(String id);
    public String DemandeupdateProduct(String id , User userDetails , Product p );
    public List<Product> findAllProduct();
    public String deleteProduct(String id  , User userDetails);

    public  Product DeposerProductVendeur(Product p) ;

    public List<Product> getProductsOfTypeProductTrue();

    List<Product> getFirst20ProductsOrderByPublicationDate();

    List<Product> getLatestPromotionProducts();

}
