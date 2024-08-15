package com.backend.ecommerce.application.dto.product.category;

import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        String description
) {

}
