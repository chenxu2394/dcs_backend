package com.backend.ecommerce.application;

import com.backend.ecommerce.abstraction.OrderService;
import com.backend.ecommerce.application.dto.CreateOrderProductDto;
import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderDetailsDto;
import com.backend.ecommerce.application.dto.order.OrderDetailsDto;
import com.backend.ecommerce.application.dto.order.OrderUpdateDto;
import com.backend.ecommerce.application.mapper.OrderMapper;
import com.backend.ecommerce.application.dto.order.CreateOrderDto;
import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderDto;
import com.backend.ecommerce.application.mapper.OrderProductMapper;
import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.OrderProduct;
import com.backend.ecommerce.domain.entities.Payment;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaOrderProductRepository;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaOrderRepository;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  JpaOrderRepository jpaOrderRepository;
  @Autowired
  JpaOrderProductRepository jpaOrderProductRepository;
  @Autowired
  JpaPaymentRepository jpaPaymentRepository;


  @Autowired
  OrderMapper orderMapper;
  @Autowired
  OrderProductMapper orderProductMapper;

  @Override
  public List<IOrderDto> getAllOrders() {
    return jpaOrderRepository.getAllOrders();
  }

  @Override
  public List<IOrderDto> getOrdersByUserId(UUID id){
    return jpaOrderRepository.getUserOrders(id);
  }

  @Override
  public List<IOrderDto> getAllOrdersByPaymentStatus(boolean status){
    return jpaOrderRepository.getAllOrdersByPaymentStatus(status);
  }

  @Override
  public Optional<OrderDetailsDto> findOrder(UUID id) {
    Optional<IOrderDetailsDto> order = jpaOrderRepository.getOrderDetails(id);
    return order.map(iOrderDetailsDto -> orderMapper.toOrderDetailsDtoFromInterface(iOrderDetailsDto));
  }

  @Override
  public Optional<OrderDetailsDto> updateOrder(UUID id, OrderUpdateDto orderUpdate) {
    Optional<Order> foundOrder = jpaOrderRepository.findById(id);

    if (foundOrder.isEmpty()) return Optional.empty();

    Order order = foundOrder.get();

    order.setId(id);
    order.setCity(orderUpdate.city());
    order.setStreet(orderUpdate.street());
    order.setPostNumber(orderUpdate.postNumber());
    order.setStatus(orderUpdate.status());

    jpaOrderRepository.updateOrder(order);
    Optional<IOrderDetailsDto> newOrder = jpaOrderRepository.getOrderDetails(id);
    return newOrder.map(iOrderDetailsDto -> orderMapper.toOrderDetailsDtoFromInterface(iOrderDetailsDto));
  }

  @Override
  public boolean deleteOrder(UUID id) {
    if (jpaOrderRepository.findById(id).isPresent()) {
      jpaOrderRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  public Optional<OrderDetailsDto> createNewOrder(CreateOrderDto createOrderDto){
    try {
      List<OrderProduct> opList = new ArrayList<>();
      Order order = orderMapper.toOrderFromCreateOrderDto(createOrderDto);
      Payment payment = order.getPayment();
      payment.setOrder(order);

      Order newOrder = jpaOrderRepository.save(order);

      for (CreateOrderProductDto op : createOrderDto.products()){
        opList.add(orderProductMapper.toOrderProductFromCreateOrderDto(op, newOrder.getId()));
      }
      
      jpaOrderProductRepository.saveAllAndFlush(opList);
      jpaPaymentRepository.save(payment);

      return findOrder(newOrder.getId());
    } catch (Exception error) {
      return Optional.empty();
    }
  }

}
