package com.backend.ecommerce.presentation;

import com.backend.ecommerce.abstraction.PaymentService;
import com.backend.ecommerce.application.dto.payment.UpdatePaymentDto;
import com.backend.ecommerce.application.dto.payment.PaymentDto;
import com.backend.ecommerce.domain.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;


  @GetMapping
  public List<PaymentDto> getAllPayments() {
    return paymentService.getAllPayments();
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Optional<PaymentDto>> updatePayment(@PathVariable UUID id, @RequestBody UpdatePaymentDto updatePaymentDto){
    Optional<PaymentDto> updatedPayment = paymentService.updatePayment(updatePaymentDto, id);
    if (updatedPayment.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(updatedPayment);
  }

}
