package com.backend.ecommerce.domain.interfaces;

import com.backend.ecommerce.domain.entities.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository {
    public Optional<Category> findById(int id);
}
