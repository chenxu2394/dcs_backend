package com.backend.ecommerce.domain.entities.dtoInterfaces.order;

import java.util.UUID;

public class CreatePaymentDto {
  public UUID orderId;
  public boolean paymentStatus;
  public float amount;
  public String paymentCity;
  public String paymentStreet;
  public String getPaymentPostNumber;

  public CreatePaymentDto(UUID orderId, boolean paymentStatus, float amount, String paymentCity, String paymentStreet, String getPaymentPostNumber) {
    this.orderId = orderId;
    this.paymentStatus = paymentStatus;
    this.amount = amount;
    this.paymentCity = paymentCity;
    this.paymentStreet = paymentStreet;
    this.getPaymentPostNumber = getPaymentPostNumber;
  }
}
