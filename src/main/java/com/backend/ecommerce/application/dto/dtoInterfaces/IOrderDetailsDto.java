package com.backend.ecommerce.application.dto.dtoInterfaces;

import java.util.Date;
import java.util.UUID;

public interface IOrderDetailsDto {
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
