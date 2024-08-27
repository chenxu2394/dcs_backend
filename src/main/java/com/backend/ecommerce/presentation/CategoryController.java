package com.backend.ecommerce.presentation;

import com.backend.ecommerce.abstraction.CategoryService;
import com.backend.ecommerce.application.dto.product.category.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getCategories(){
        var categories = categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }
}
