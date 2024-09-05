package com.backend.ecommerce.application.dto.user;

import java.util.UUID;

import com.backend.ecommerce.domain.enums.UserRole;

// This class is used to return a user with all info, less the password
public record ReturnedDto(
    UUID id,
    String name,
    String email,
    UserRole userRole) {
}
