package com.backend.ecommerce.presentation;

import com.backend.ecommerce.application.ProductService;
import com.backend.ecommerce.infastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
//    @Autowired
//    private ProductService productService;

    public String getProduct(){
//        var productRepository = new ProductRepository();
//        var productService = new ProductService(productRepository);

        return "Hello World";
    }
}
