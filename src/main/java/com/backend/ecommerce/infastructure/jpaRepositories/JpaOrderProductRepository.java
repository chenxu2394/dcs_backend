package com.backend.ecommerce.infastructure.jpaRepositories;

import com.backend.ecommerce.domain.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaOrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}
