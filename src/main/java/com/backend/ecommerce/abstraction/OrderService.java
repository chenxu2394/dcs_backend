package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.OrderListDto;
import com.backend.ecommerce.application.dto.order.SingleOrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
  public List<OrderListDto> getAllOrders();
  public List<OrderListDto> getAllOrdersByPaymentStatus(boolean status);
  public List<OrderListDto> getUsersOrders(String id);
  public Optional<SingleOrderDto> findOrder(String id);

  public Order createNewOrder(Order order);
  public Optional<Order> updateOrder(Order order);
  public boolean deleteOrder(Integer id);

  Optional<SingleOrderDto> createNewOrder(String teksti);
}
