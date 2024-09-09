package com.backend.ecommerce.presentation;

import com.backend.ecommerce.abstraction.CategoryService;
import com.backend.ecommerce.application.dto.product.category.CategoryDto;
import com.backend.ecommerce.application.dto.product.category.CreateCategoryDto;
import com.backend.ecommerce.domain.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryDto category){
        var result = categoryService.addCategory(category);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        var result = categoryService.updateCategory(category);
        return ResponseEntity.ok(result);
    }
}
