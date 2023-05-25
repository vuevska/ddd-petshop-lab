package com.example.shopusers.services;

import com.example.shopusers.domain.models.User;
import com.example.shopusers.domain.models.UserId;

import java.util.List;

public interface UserService {
    User findById(UserId id);
    List<User> getAll();
}
