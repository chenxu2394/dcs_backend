package com.backend.ecommerce.application.dto.product;

import java.util.UUID;
import java.util.List;

public record UpdateProductDto(
                UUID id,
                String name,
                String description,
                int quantity,
                double price,
                int discount,
                UUID categoryId,
                List<String> imageUrls) {
}
