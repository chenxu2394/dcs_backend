package com.backend.ecommerce.application.dto.order;

import com.backend.ecommerce.domain.entities.OrderProduct;

import java.util.List;
import java.util.UUID;

public record OrderUpdateDto(String status, String city, String street, String postNumber) {
}
