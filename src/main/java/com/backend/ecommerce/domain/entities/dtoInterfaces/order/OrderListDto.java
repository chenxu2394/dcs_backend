package com.backend.ecommerce.domain.entities.dtoInterfaces.order;

import java.util.UUID;

public interface OrderListDto {
  UUID getId();
  UUID getUserId();
  String getUserName();
  String getOrderStatus();
  boolean getPaymentStatus();
  float getAmount();
}
