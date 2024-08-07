package com.backend.ecommerce.domain.interfaces;

import com.backend.ecommerce.domain.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    public List<Product> getAllProducts();
    public Optional<Product> getProductById(int id);
    public Product addProduct(Product product);
    public Optional<Product> updateProduct(Product product);
    public boolean deleteProduct(int id);
}
