package com.codewithmosh.store.controller;

import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@Getter
@RestController
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}
