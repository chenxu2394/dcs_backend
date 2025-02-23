package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.application.dto.product.CreateProductDto;
import com.backend.ecommerce.application.dto.product.ProductDto;
import com.backend.ecommerce.application.dto.product.UpdateProductDto;
import com.backend.ecommerce.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    public List<ProductDto> getAllProducts();
    public List<ProductDto> filterProductsBySearch(String search);
    public Page<ProductDto> filterProductsBy(
            String search,
            List<String> categories,
            Double minPrice,
            Double maxPrice,
            Pageable pageable
    );
    public Optional<ProductDto> getProductById(UUID id);
    public ProductDto addProduct(CreateProductDto product);
    public ProductDto updateProduct(UpdateProductDto product);
    public void deleteProduct(UUID id);
}
