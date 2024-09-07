package com.backend.ecommerce.infastructure.repositories;

import com.backend.ecommerce.domain.entities.User;
import com.backend.ecommerce.domain.interfaces.UserRepository;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaUserRepository;
import com.backend.ecommerce.shared.exceptions.BadRequestException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return jpaUserRepository.findAll().stream().toList();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        jpaUserRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        jpaUserRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(UUID id) {
        jpaUserRepository.findById(id).ifPresentOrElse(
                jpaUserRepository::delete,
                () -> {
                    throw new BadRequestException("User not found");
                }
        );
    }
}
