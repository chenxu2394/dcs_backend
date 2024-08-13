package com.backend.ecommerce.application;

import com.backend.ecommerce.abstraction.UserService;
import com.backend.ecommerce.domain.entities.User;
import com.backend.ecommerce.domain.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    public Optional<User> getUserById(UUID id) {
        return userRepository.getUserById(id);
    }

}
