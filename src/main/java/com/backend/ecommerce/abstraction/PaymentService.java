package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.application.dto.payment.UpdatePaymentDto;
import com.backend.ecommerce.application.dto.payment.PaymentDto;
import com.backend.ecommerce.domain.entities.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface PaymentService {
  public List<PaymentDto> getAllPayments();
  public Optional<PaymentDto> updatePayment(UpdatePaymentDto updatePaymentDto, UUID uuid);
}
