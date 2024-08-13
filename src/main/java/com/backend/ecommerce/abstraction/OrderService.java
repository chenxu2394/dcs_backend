package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.application.dto.order.OrderUpdateDto;
import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderListDto;
import com.backend.ecommerce.application.dto.order.SingleOrderDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
  public List<IOrderListDto> getAllOrders();
  public List<IOrderListDto> getAllOrdersByPaymentStatus(boolean status);
  public List<IOrderListDto> getUsersOrders(String id);
  public Optional<SingleOrderDto> findOrder(UUID id);
  public Optional<SingleOrderDto> createNewOrder(String userInput);
  public boolean deleteOrder(UUID id);
  public Optional<SingleOrderDto> updateOrder(UUID id, OrderUpdateDto orderUpdate);
}
