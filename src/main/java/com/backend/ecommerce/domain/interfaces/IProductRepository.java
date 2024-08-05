package com.backend.ecommerce.domain.interfaces;

import com.backend.ecommerce.domain.entities.Product;

import java.util.List;

public interface IProductRepository {
    public List<Product> getAllProducts();
}
