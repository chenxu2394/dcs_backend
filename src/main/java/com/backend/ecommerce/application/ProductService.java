package com.backend.ecommerce.application;

import com.backend.ecommerce.domain.entities.Product;
import com.backend.ecommerce.domain.interfaces.IProductRepository;

import java.util.List;

public class ProductService {

    private IProductRepository productRepository;
    public ProductService(IProductRepository productRepository) {

    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}
