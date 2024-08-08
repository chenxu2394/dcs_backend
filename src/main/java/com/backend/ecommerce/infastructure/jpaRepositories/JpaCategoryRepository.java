package com.backend.ecommerce.infastructure.jpaRepositories;

import com.backend.ecommerce.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCategoryRepository extends JpaRepository<Category, UUID> {
}
