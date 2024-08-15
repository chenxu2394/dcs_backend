package com.backend.ecommerce.application.dto.product;

import java.util.UUID;

public record ShortProductDetailDto(UUID id, String name, float price) {
}
