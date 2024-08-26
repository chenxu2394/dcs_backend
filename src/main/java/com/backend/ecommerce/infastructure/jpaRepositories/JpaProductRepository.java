package com.backend.ecommerce.infastructure.jpaRepositories;


import com.backend.ecommerce.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT * FROM ecommerce.product p WHERE p.name ILIKE %:search%", nativeQuery = true)
    List<Product> findByNameContaining(String search);
}
