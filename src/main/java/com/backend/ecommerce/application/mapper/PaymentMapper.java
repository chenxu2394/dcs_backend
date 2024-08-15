package com.backend.ecommerce.application.mapper;

import com.backend.ecommerce.application.dto.order.CreateOrderDto;
import com.backend.ecommerce.domain.entities.Payment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PaymentMapper {
  /*
  @Mapping(target="order.id", source="uuid")
  @Mapping(target="amount", source="source.payment.amount")
  @Mapping(target="city", source="source.payment.city")
  @Mapping(target="street", source="source.payment.street")
  @Mapping(target="postNumber", source="source.payment.postNumber")
  Payment toPaymentFromCreateOrderDto(CreateOrderDto source, UUID uuid);

   */
}
