package com.backend.ecommerce.infastructure.repositories;

import com.backend.ecommerce.domain.entities.Product;
import com.backend.ecommerce.domain.interfaces.ProductRepository;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaProductRepository;
import com.backend.ecommerce.shared.exceptions.BadRequestException;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return jpaProductRepository.findAll();
    }

    @Override
    public List<Product> filterProductsBySearch(String search) {
        return jpaProductRepository.findByNameContaining(search);
    }

    @Override
    public Page<Product> filterProductsBy(String search, List<String> categories, double minPrice, double maxPrice, Pageable pageable) {
        return jpaProductRepository.filterBy(search, categories, minPrice, maxPrice, pageable);
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return jpaProductRepository.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        jpaProductRepository.save(product);

        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        jpaProductRepository.save(product);

        return product;
    }

    @Override
    public void deleteProduct(UUID id) {
        var originalProduct = jpaProductRepository.findById(id);

        if (originalProduct.isEmpty()) {
            throw new BadRequestException("Product not found");
        }
        jpaProductRepository.deleteById(id);
    }

}
