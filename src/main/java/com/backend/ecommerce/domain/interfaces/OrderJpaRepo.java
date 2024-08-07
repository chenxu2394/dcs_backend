package com.backend.ecommerce.domain.interfaces;

import com.backend.ecommerce.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderJpaRepo extends JpaRepository<Order, UUID> {
}
