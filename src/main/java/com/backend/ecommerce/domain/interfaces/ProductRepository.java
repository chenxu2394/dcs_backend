package com.backend.ecommerce.domain.interfaces;

import com.backend.ecommerce.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    public List<Product> getAllProducts();
    public List<Product> filterProductsBySearch(String search);
    public Page<Product> filterProductsBy(String search, List<String> categories, double minPrice, double maxPrice, Pageable pageable);
    public Optional<Product> getProductById(UUID id);
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(UUID id);
}
