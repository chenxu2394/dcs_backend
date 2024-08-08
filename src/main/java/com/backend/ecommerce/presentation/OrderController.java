package com.backend.ecommerce.presentation;

import com.backend.ecommerce.abstraction.OrderService;
import com.backend.ecommerce.domain.entities.Order;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.OrderListDto;
import com.backend.ecommerce.domain.entities.dtoInterfaces.order.SingleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping
  public List<OrderListDto> findAll(@RequestParam("paid") Optional<Boolean> status) {
    System.out.println(status);
    if (status.isPresent()){
      return orderService.getAllOrdersByPaymentStatus(status.get());
    }
    return orderService.getAllOrders();
  }
/*
  GetMapping
  public List<OrderListDto> findAll(@RequestParam("paid") Optional<boolean> status){
    return orderService.getAllOrdersByPaymentStatus(status);
  }
  */

  @GetMapping("/{id}")
  public ResponseEntity<Optional<SingleOrder>> findOne(@PathVariable Integer id) {
    Optional<SingleOrder> order = orderService.findOrder(id);
    if (order.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(order);
  }
}
