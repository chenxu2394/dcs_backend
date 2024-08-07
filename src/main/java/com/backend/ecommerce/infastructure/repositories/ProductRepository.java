package com.backend.ecommerce.infastructure.repositories;

import com.backend.ecommerce.domain.entities.Product;
import com.backend.ecommerce.domain.interfaces.IProductRepository;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaProductRepository;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepository(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return jpaProductRepository.findAll().stream().toList();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return jpaProductRepository.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        jpaProductRepository.save(product);

        return product;
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        var originalProduct = jpaProductRepository.findById(product.getId());

        if (originalProduct.isEmpty()) {
            return Optional.empty();
        }

        jpaProductRepository.save(product);

        return Optional.of(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        var originalProduct = jpaProductRepository.findById(id);

        if (originalProduct.isEmpty()) {
            return false;
        }
        jpaProductRepository.deleteById(id);
        return true;
    }

}
