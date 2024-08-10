package com.backend.ecommerce.domain.entities.dtoInterfaces.order;

import java.util.Date;
import java.util.UUID;

public interface SingleOrderDto {
  UUID getId();
  UUID getUserId();
  UUID getPaymentId();
  String getUserName();
  String getUserEmail();
  String getOrderStatus();
  String getShipmentCity();
  String getShipmentStreet();
  String getShipmentPostNumber();
  Date getOrderDate();
  float getAmount();
  String getBillingCity();
  String getBillingStreet();
  String getBillingPostNumber();
  boolean getPaymentStatus();
}
