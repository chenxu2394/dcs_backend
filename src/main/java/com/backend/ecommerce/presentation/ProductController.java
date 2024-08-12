package com.backend.ecommerce.presentation;

import com.backend.ecommerce.application.ProductService;
import com.backend.ecommerce.application.dto.product.CreateProductRequest;
import com.backend.ecommerce.domain.entities.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getProduct(){
        var products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest product){
        var result = productService.addProduct(product);

        return result.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") UUID id){
        var product = productService.getProductById(id);

        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
