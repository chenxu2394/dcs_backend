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
  public List<OrderListDto> findAll(@RequestParam("paid") Optional<Boolean> status, @RequestParam("user") Optional<String> id) {
    System.out.println(status);
    if (status.isPresent()){
      return orderService.getAllOrdersByPaymentStatus(status.get());
    }
    if (id.isPresent()){
      return orderService.getUsersOrders(id.get());
    }
    return orderService.getAllOrders();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<SingleOrder>> findOne(@PathVariable String id) {
    Optional<SingleOrder> order = orderService.findOrder(id);
    if (order.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(order);
  }
}
