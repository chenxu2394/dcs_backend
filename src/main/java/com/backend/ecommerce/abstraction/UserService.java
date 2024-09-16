package com.backend.ecommerce.abstraction;

import com.backend.ecommerce.application.dto.user.ReturnedDto;
import com.backend.ecommerce.application.dto.user.UpdateUserDto;
import com.backend.ecommerce.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    public List<ReturnedDto> getAllUsers();
    public Optional<User> getUserById(UUID id);
    public void deleteUser(UUID id);
    public ReturnedDto updateUser(UpdateUserDto user);
}
