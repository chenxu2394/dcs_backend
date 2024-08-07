package com.backend.ecommerce.presentation;


import com.backend.ecommerce.domain.entities.User;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final JpaUserRepository jpaUserRepository;

    public UserController(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUser(){
        //Shortcut, using the DB directly
        var result = jpaUserRepository.findAll().stream().toList();

        return ResponseEntity.ok(result);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
