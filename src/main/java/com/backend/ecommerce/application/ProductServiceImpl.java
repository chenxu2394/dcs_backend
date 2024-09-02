package com.backend.ecommerce.application;

import com.backend.ecommerce.abstraction.ProductService;
import com.backend.ecommerce.application.dto.product.CreateProductDto;
import com.backend.ecommerce.application.dto.product.ProductDto;
import com.backend.ecommerce.application.dto.product.UpdateProductDto;
import com.backend.ecommerce.application.mapper.ProductMapper;
import com.backend.ecommerce.domain.entities.Category;
import com.backend.ecommerce.domain.interfaces.CategoryRepository;
import com.backend.ecommerce.domain.interfaces.ProductRepository;
import com.backend.ecommerce.shared.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAllProducts() {
        var products = productRepository.getAllProducts();

        return productMapper.toProductListDto(products);
    }

    public List<ProductDto> filterProductsBySearch(String search) {
        var products = productRepository.filterProductsBySearch(search);

        return productMapper.toProductListDto(products);
    }

    public List<ProductDto> filterProductsBy(String search, List<String> categories, Double minPrice, Double maxPrice) {
        // Complex validation and business rule enforcement
        if (minPrice == null) {
            minPrice = 0.0;
        }
        if (maxPrice == null) {
            maxPrice = Double.MAX_VALUE;
        }
        if (minPrice < 0 || maxPrice < 0) {
            throw new BadRequestException("Price cannot be negative");
        }
        if (minPrice > maxPrice) {
            throw new BadRequestException("Min price cannot be greater than max price");
        }

        // Business logic based default setting
        if (categories == null || categories.isEmpty()) {
            categories = categoryRepository.getAllCategories().stream()
                    .map(Category::getName)
                    .collect(Collectors.toList());
        }

        // Retrieve filtered products
        var products = productRepository.filterProductsBy(search, categories, minPrice, maxPrice);
        return productMapper.toProductListDto(products);
    }


    public Optional<ProductDto> getProductById(UUID id) {
        var product = productRepository.getProductById(id);

        return product.map(productMapper::toProductDto);
    }

    public ProductDto addProduct(CreateProductDto product) {
        var newProduct = productMapper.toProduct(product);

        var category = categoryRepository.findById(product.categoryId())
                .orElseThrow(() -> new BadRequestException("Category not found"));

        newProduct.setCategory(category);

        newProduct = productRepository.addProduct(newProduct);

        return productMapper.toProductDto(newProduct);
    }

    public ProductDto updateProduct(UpdateProductDto product) {
        var updateProductDomain = productMapper.toProductFromUpdate(product);

        var oldProduct = productRepository.getProductById(product.id());

        if (oldProduct.isEmpty()){
            throw new BadRequestException("Product not found");
        }

        var res = productRepository.updateProduct(updateProductDomain);

        return productMapper.toProductDto(res);
    }
    public void deleteProduct(UUID id) {
        productRepository.deleteProduct(id);
    }
}
