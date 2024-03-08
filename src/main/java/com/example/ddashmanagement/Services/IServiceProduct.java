package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Entites.User;

import java.util.List;

public interface IServiceProduct {

    public Product createProduct(Product p);

    public Product findProductById(String id);
    public String DemandeupdateProduct(String id , User userDetails , Product p );
    public List<Product> findAllProduct();
    public void deleteProduct(Product c);
    public boolean ProductExists(String id);
}
