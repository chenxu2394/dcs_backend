package com.backend.ecommerce.infastructure.repositories;

import com.backend.ecommerce.domain.entities.Category;
import com.backend.ecommerce.domain.interfaces.ICategoryRepository;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoryRepository implements ICategoryRepository {
    private final JpaCategoryRepository jpaCategoryRepository;

    public CategoryRepository(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return jpaCategoryRepository.findById(id);
    }
}
