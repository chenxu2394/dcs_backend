package com.backend.ecommerce.presentation;


import com.backend.ecommerce.application.UserService;
import com.backend.ecommerce.domain.entities.User;
import com.backend.ecommerce.infastructure.jpaRepositories.JpaUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final JpaUserRepository jpaUserRepository;
    private final UserService userService;

    public UserController(JpaUserRepository jpaUserRepository, UserService userService) {
        this.jpaUserRepository = jpaUserRepository;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUser(){
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
