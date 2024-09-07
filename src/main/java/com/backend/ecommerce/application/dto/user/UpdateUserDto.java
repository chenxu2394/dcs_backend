package com.backend.ecommerce.application.dto.user;
import java.util.UUID;
import com.backend.ecommerce.domain.enums.UserRole;

public record UpdateUserDto(
        UUID id,
        String name,
        String email,
        UserRole userRole
) {
}
