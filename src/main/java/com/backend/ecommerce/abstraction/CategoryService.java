package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.application.dto.product.category.CategoryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    public List<CategoryDto> getAllCategories();
    public Optional<CategoryDto> getCategoryById(UUID id);
}
