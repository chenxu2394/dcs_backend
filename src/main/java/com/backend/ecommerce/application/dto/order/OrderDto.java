package com.backend.ecommerce.application.dto.order;

import java.util.UUID;

public record OrderDto(
        UUID id,
        UUID userId,
        String userName,
        String orderStatus,
        boolean paymentStatus,
        float amount
) { }
