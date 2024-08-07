package com.backend.ecommerce.infastructure.jpaRepositories;

import com.backend.ecommerce.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Integer> {
}
