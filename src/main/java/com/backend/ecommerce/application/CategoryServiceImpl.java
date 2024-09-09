package com.backend.ecommerce.application;

import com.backend.ecommerce.abstraction.CategoryService;
import com.backend.ecommerce.application.dto.product.category.CategoryDto;
import com.backend.ecommerce.application.dto.product.category.CreateCategoryDto;
import com.backend.ecommerce.application.mapper.CategoryMapper;
import com.backend.ecommerce.domain.entities.Category;
import com.backend.ecommerce.domain.interfaces.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        var categories = categoryRepository.getAllCategories();
        return categoryMapper.toCategoryListDto(categories);
    }

    @Override
    public Optional<CategoryDto> getCategoryById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Category addCategory(CreateCategoryDto category) {
        var categoryEntity = categoryMapper.toCategory(category);
        return categoryRepository.addCategory(categoryEntity);
    }
}
