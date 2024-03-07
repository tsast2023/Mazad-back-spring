package com.example.ddashmanagement.Services.Impl;

import com.example.ddashmanagement.Entites.Product;
import com.example.ddashmanagement.Repository.ProductRepository;
import com.example.ddashmanagement.Services.IServiceProduct;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceProductImpl implements IServiceProduct {
    private final ProductRepository productRepository;

    public ServiceProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product createProduct(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product findProductById(String id) {
        return null;
    }

    @Override
    public Product updateProduct(String id, Product p) {
        return null;
    }

    @Override
    public List<Product> findAllProduct() {
        return null;
    }

    @Override
    public void deleteProduct(Product c) {

    }

    @Override
    public boolean ProductExists(String id) {
        return false;
    }
}
