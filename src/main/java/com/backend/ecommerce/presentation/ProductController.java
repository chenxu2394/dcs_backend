package com.backend.ecommerce.presentation;

import com.backend.ecommerce.application.ProductService;
import com.backend.ecommerce.domain.entities.Product;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaProductRepository;
import com.backend.ecommerce.infastructure.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

//    private final ProductService productService;
    private final JpaProductRepository jpaProductRepository;


    public ProductController(JpaProductRepository jpaProductRepository) {
//        this.productService = productService;
        this.jpaProductRepository = jpaProductRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProduct(){
        //Shortcut, using the DB directly
        var result = jpaProductRepository.findAll().stream().toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        jpaProductRepository.save(product);

        return ResponseEntity.ok(product);
    }
}
