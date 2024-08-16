package com.backend.ecommerce.application.dto.payment;

public record CreatePayment(
        float amount,
        String city,
        String street,
        String postNumber,
        boolean payment_status
) {}
