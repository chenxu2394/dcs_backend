package com.backend.ecommerce.application.dto.dtoInterfaces;

import java.util.UUID;

public interface IOrderListDto {
  UUID getId();
  UUID getUserId();
  String getUserName();
  String getOrderStatus();
  boolean getPaymentStatus();
  float getAmount();
}
