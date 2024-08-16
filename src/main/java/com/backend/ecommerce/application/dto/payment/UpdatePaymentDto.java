package com.backend.ecommerce.application.dto.payment;

public record UpdatePaymentDto(
        float amount,
        String city,
        String street,
        String postNumber,
        boolean paymentStatus
) {}
