package com.backend.ecommerce.domain.entities.dtoInterfaces.order;

import java.util.Date;
import java.util.UUID;


public class CreateOrderDto {
  public UUID userId;
  public String shipmentCity;
  public String shipmentStreet;
  public String shipmentPostNumber;
  public String orderStatus;
  public Date orderDate;

  public CreateOrderDto(UUID userId, String shipmentCity, String shipmentStreet, String shipmentPostNumber, String orderStatus, Date orderDate) {
    this.userId = userId;
    this.shipmentCity = shipmentCity;
    this.shipmentStreet = shipmentStreet;
    this.shipmentPostNumber = shipmentPostNumber;
    this.orderStatus = orderStatus;
    this.orderDate = orderDate;
  }
}
