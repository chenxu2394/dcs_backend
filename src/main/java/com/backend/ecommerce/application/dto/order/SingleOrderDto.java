package com.backend.ecommerce.application.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.UUID;
/*
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
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public record SingleOrderDto (
  UUID id,
  UUID userId,
  UUID paymentId,
  String userName,
  String userEmail,
  String orderStatus,
  String shipmentCity,
  String shipmentStreet,
  String shipmentPostNumber,
  Date orderDate,
  float amount,
  String paymentCity,
  String paymentStreet,
  String paymentPostNumber,
  boolean paymentStatus
){};