package com.backend.ecommerce.infastructure.repositories;

import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.interfaces.IOrderRepository;
import com.backend.ecommerce.infastructure.Database;

import java.util.List;
import java.util.Optional;

public class OrderRepository implements IOrderRepository {
  private final List<Order> orders;

  public OrderRepository(Database database) {
    this.orders = database.getOrders();
  }

  @Override
  public List<Order> getAllOrders() {
    return List.of();
  }

  @Override
  public Optional<Order> getOrderById() {
    return Optional.empty();
  }

  @Override
  public boolean createNewOrder() {
    return false;
  }

  @Override
  public boolean updateOrder() {
    return false;
  }

  @Override
  public boolean deleteOrder() {
    return false;
  }
}
