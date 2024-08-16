package com.backend.ecommerce.application.dto.order;

import com.backend.ecommerce.application.dto.CreateOrderProductDto;
import com.backend.ecommerce.application.dto.payment.CreatePayment;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public record CreateOrderDto (
  UUID user_id,
  String city,
  String street,
  String postNumber,
  String status,
  Date date,
  List<CreateOrderProductDto> products,
  CreatePayment payment
){}
