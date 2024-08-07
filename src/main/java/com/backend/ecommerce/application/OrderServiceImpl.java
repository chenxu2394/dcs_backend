package com.backend.ecommerce.application;

import com.backend.ecommerce.abstraction.OrderService;
import com.backend.ecommerce.domain.entities.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
  @Override
  public List<Order> getAllOrders() {
    return List.of();
  }

  @Override
  public Optional<Order> findOrder(UUID id) {
    return Optional.empty();
  }

  @Override
  public Order createNewOrder(Order order) {
    return null;
  }

  @Override
  public Order updateOrder(Order order) {
    return null;
  }

  @Override
  public boolean deleteOrder(UUID id) {
    return false;
  }
}
