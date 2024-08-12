package com.backend.ecommerce.presentation;

import com.backend.ecommerce.abstraction.OrderService;
import com.backend.ecommerce.application.dto.dtoInterfaces.IOrderListDto;
import com.backend.ecommerce.application.dto.order.OrderUpdateDto;
import com.backend.ecommerce.application.dto.order.SingleOrderDto;
import com.backend.ecommerce.domain.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;


  @GetMapping
  public List<IOrderListDto> findAll(@RequestParam("paid") Optional<Boolean> status, @RequestParam("user") Optional<String> id) {
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
  public ResponseEntity<Optional<SingleOrderDto>> findOne(@PathVariable UUID id) {
    Optional<SingleOrderDto> order = orderService.findOrder(id);
    if (order.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(order);
  }

  @PostMapping
  public ResponseEntity<Optional<SingleOrderDto>> createNewOrder(@RequestBody String userInput){
    Optional<SingleOrderDto> newOrder = orderService.createNewOrder(userInput);
    if (newOrder.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(newOrder);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Optional<Order>> updateOrder(@PathVariable UUID id, @RequestBody OrderUpdateDto orderUpdate) {
    Optional<Order> updatedOrder = orderService.updateOrder(id, orderUpdate);
    if (updatedOrder.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(updatedOrder);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteOrder(@PathVariable UUID id) {
    boolean answer = orderService.deleteOrder(id);
    if (answer) return new ResponseEntity<>(HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }
}
