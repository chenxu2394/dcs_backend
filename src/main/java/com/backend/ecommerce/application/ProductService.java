package com.backend.ecommerce.application;

import com.backend.ecommerce.application.dto.CreateProductRequest;
import com.backend.ecommerce.domain.entities.Product;
import com.backend.ecommerce.domain.interfaces.ICategoryRepository;
import com.backend.ecommerce.domain.interfaces.IProductRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductService(IProductRepository productRepository,
                          ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
    public Optional<Product> getProductById(UUID id) {
        return productRepository.getProductById(id);
    }
    public Optional<Product> addProduct(CreateProductRequest product) {
        var newProduct = new Product();
        //TODO: Map the DTO using mapper
        newProduct.setName(product.name());

        var category = categoryRepository.findById(product.categoryId());

        if (category.isEmpty()){
            return Optional.empty();
        }

        newProduct.setCategory(category.get());
        return Optional.ofNullable(productRepository.addProduct(newProduct));
    }
    public Optional<Product> updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }
    public boolean deleteProduct(UUID id) {
        return productRepository.deleteProduct(id);
    }
}
