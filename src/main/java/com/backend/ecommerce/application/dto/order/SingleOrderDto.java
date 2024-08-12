package com.backend.ecommerce.application.dto.order;

import com.backend.ecommerce.application.dto.product.ShortProductListDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
  String billingCity,
  String billingStreet,
  String billingPostNumber,
  boolean paymentStatus
){};