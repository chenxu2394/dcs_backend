package com.backend.ecommerce.application.mapper;

import com.backend.ecommerce.application.dto.payment.PaymentDto;
import com.backend.ecommerce.domain.entities.Payment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PaymentMapper {

  @Mapping(target = "orderId", source = "payment.order.id")
  PaymentDto toPaymentDtoFromPayment(Payment payment);
}
