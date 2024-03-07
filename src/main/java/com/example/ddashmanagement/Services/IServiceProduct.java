package com.example.ddashmanagement.Services;

import com.example.ddashmanagement.Entites.CategoryFille;
import com.example.ddashmanagement.Entites.Product;

import java.util.List;

public interface IServiceProduct {

    public Product createProduct(Product p);

    public Product findProductById(String id);
    public Product updateProduct(String id ,Product p);
    public List<Product> findAllProduct();
    public void deleteProduct(Product c);
    public boolean ProductExists(String id);
}
