package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.application.dto.order.CreateOrderDto;
import com.backend.ecommerce.application.dto.order.OrderDetailsDto;
import com.backend.ecommerce.application.dto.order.OrderUpdateDto;
import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
  public List<IOrderDto> getAllOrders();
  public List<IOrderDto> getAllOrdersByPaymentStatus(boolean status);
  public List<IOrderDto> getOrdersByUserId(UUID id);
  public Optional<OrderDetailsDto> findOrder(UUID id);
  public Optional<OrderDetailsDto> createNewOrder(CreateOrderDto createOrderDto);
  public boolean deleteOrder(UUID id);
  public Optional<OrderDetailsDto> updateOrder(UUID id, OrderUpdateDto orderUpdate);
}
