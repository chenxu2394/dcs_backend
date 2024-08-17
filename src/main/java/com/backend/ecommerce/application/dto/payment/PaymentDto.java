package com.backend.ecommerce.application.dto.payment;

import java.util.UUID;

public record PaymentDto(
        UUID id,
        UUID orderId,
        float amount,
        String city,
        String street,
        String postNumber,
        boolean paymentStatus
) {
}
