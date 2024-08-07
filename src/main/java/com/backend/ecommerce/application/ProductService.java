package com.backend.ecommerce.application;

import com.backend.ecommerce.domain.entities.Product;
import com.backend.ecommerce.domain.interfaces.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private IProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}
