package com.example.shopusers.services.impl;

import com.example.shopusers.domain.exceptions.UserNotFoundException;
import com.example.shopusers.domain.models.User;
import com.example.shopusers.domain.models.UserId;
import com.example.shopusers.domain.repository.UserRepository;
import com.example.shopusers.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(UserId id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
