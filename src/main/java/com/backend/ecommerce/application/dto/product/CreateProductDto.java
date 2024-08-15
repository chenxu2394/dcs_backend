package com.backend.ecommerce.application.dto.product;

import java.util.UUID;

public record CreateProductDto(
        String name,
        String description,
        int quantity,
        double price,
        int discount,
        UUID categoryId) {
}
