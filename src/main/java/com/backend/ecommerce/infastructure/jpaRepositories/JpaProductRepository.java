package com.backend.ecommerce.infastructure.jpaRepositories;


import com.backend.ecommerce.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT * FROM ecommerce.product p WHERE p.name ILIKE %:search%", nativeQuery = true)
    List<Product> findByNameContaining(String search);

    @Query(value = "SELECT p.* FROM ecommerce.product p " +
            "JOIN ecommerce.category c ON p.category_id = c.id " +
            "WHERE p.name ILIKE %:search% " +
            "AND c.name IN :categories " +
            "AND p.price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
    List<Product> filterBy(
            @Param("search") String search,
            @Param("categories") List<String> categories,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice);
}
