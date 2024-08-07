package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.domain.entities.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
  public List<Order> getAllOrders();
  public Optional<Order> findOrder(UUID id);
  public Order createNewOrder(Order order);
  public Order updateOrder(Order order);
  public boolean deleteOrder(UUID id);
}
