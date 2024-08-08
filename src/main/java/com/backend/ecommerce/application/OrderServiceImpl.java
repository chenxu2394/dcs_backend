package com.backend.ecommerce.application;

import com.backend.ecommerce.abstraction.OrderService;
import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.OrderListDto;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.SingleOrder;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  private JpaOrderRepository jpaRepo;

  @Override
  public List<OrderListDto> getAllOrders() {
    return jpaRepo.getAllOrders();
  }

  @Override
  public List<OrderListDto> getAllOrdersByPaymentStatus(boolean status){
    return jpaRepo.getAllOrdersByPaymentStatus(status);
  }

  @Override
  public Optional<SingleOrder> findOrder(Integer id) {
    return jpaRepo.getSingleOrder(id);
  }

  @Override
  public Order createNewOrder(Order order) {
    return jpaRepo.save(order);
  }

  @Override
  public Optional<Order> updateOrder(Order order) {
    Optional<Order> foundOrder = jpaRepo.findById(order.getId());
    if (foundOrder.isEmpty()) return Optional.empty();
    jpaRepo.save(order);
    return Optional.empty();
  }

  @Override
  public boolean deleteOrder(Integer id) {
    if (jpaRepo.findById(id).isPresent()) {
      jpaRepo.deleteById(id);
      return true;
    }
    return false;
  }
}
