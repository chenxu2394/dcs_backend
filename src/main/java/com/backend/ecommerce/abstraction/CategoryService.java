package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.application.dto.product.category.CategoryDto;
import com.backend.ecommerce.application.dto.product.category.CreateCategoryDto;
import com.backend.ecommerce.domain.entities.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    public List<CategoryDto> getAllCategories();
    public Optional<CategoryDto> getCategoryById(UUID id);
    public Category addCategory(CreateCategoryDto category);
//    public CategoryDto updateCategory(CategoryDto category);
//    public void deleteCategory(UUID id);
}
