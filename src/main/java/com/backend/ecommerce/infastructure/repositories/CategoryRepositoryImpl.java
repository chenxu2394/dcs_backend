package com.backend.ecommerce.infastructure.repositories;

import com.backend.ecommerce.domain.entities.Category;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoryRepositoryImpl implements com.backend.ecommerce.domain.interfaces.CategoryRepository {
    private final JpaCategoryRepository jpaCategoryRepository;

    public CategoryRepositoryImpl(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return jpaCategoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return jpaCategoryRepository.findById(id);
    }

    @Override
    public Category addCategory(Category category) {
        jpaCategoryRepository.save(category);
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        jpaCategoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategory(UUID id) {
        jpaCategoryRepository.deleteById(id);
    }
}
