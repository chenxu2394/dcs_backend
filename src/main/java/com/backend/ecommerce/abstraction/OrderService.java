package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.OrderListDto;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.SingleOrder;

import java.util.List;
import java.util.Optional;

public interface OrderService {
  public List<OrderListDto> getAllOrders();
  public Optional<SingleOrder> findOrder(Integer id);
  public Order createNewOrder(Order order);
  public Optional<Order> updateOrder(Order order);
  public boolean deleteOrder(Integer id);
}
