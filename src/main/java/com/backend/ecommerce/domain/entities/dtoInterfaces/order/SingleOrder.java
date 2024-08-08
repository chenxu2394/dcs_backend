package com.backend.ecommerce.domain.entities.dtoInterfaces.order;

import java.util.Date;

public interface SingleOrder {
  int getId();
  int getUserId();
  int getPaymentId();
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
