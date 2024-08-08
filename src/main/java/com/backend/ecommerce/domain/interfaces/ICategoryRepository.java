package com.backend.ecommerce.domain.interfaces;

import com.backend.ecommerce.domain.entities.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICategoryRepository {
    public Optional<Category> findById(UUID id);
}
