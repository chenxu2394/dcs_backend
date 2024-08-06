package com.backend.ecommerce.infastructure.jpaRepositories;


import com.backend.ecommerce.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, Integer> {

}
