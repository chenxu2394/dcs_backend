package com.backend.ecommerce.application.dto;

import java.util.UUID;

public record OrderCreateDTO(UUID user_id, String status, String city, String street, String post_number) {
   }
