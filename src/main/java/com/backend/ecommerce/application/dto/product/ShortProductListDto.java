package com.backend.ecommerce.application.dto.product;

import java.util.UUID;

public record ShortProductListDto(UUID productId, String productName, float productPrice) {
}
