package com.backend.ecommerce.application.dto.product;

import com.backend.ecommerce.application.dto.product.category.CategoryDto;

import java.util.UUID;

public record ProductDto(
        UUID id,
        String name,
        String description,
        int quantity,
        double price,
        int discount,
        CategoryDto category
) {
}
