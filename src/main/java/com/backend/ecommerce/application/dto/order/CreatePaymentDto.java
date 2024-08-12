package com.backend.ecommerce.application.dto.order;

import java.util.UUID;

public class CreatePaymentDto {
  public UUID orderId;
  public boolean paymentStatus;
  public float amount;
  public String billingCity;
  public String billingStreet;
  public String billingPostNumber;

  public CreatePaymentDto(UUID orderId, boolean paymentStatus, float amount, String billingCity, String billingStreet, String billingPostNumber) {
    this.orderId = orderId;
    this.paymentStatus = paymentStatus;
    this.amount = amount;
    this.billingCity = billingCity;
    this.billingStreet = billingStreet;
    this.billingPostNumber = billingPostNumber;
  }
}
