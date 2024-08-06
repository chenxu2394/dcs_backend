package com.backend.ecommerce.domain.interfaces;

import com.backend.ecommerce.domain.entities.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {
  public List<Order> getAllOrders();
  public Optional<Order> getOrderById();
  public boolean createNewOrder();
  public boolean updateOrder();
  public boolean deleteOrder();
}
