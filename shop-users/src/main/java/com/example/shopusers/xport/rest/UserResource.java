package com.example.shopusers.xport.rest;

import com.example.shopusers.domain.models.User;
import com.example.shopusers.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }
}
