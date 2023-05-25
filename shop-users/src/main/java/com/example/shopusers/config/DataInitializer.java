package com.example.shopusers.config;

import com.example.shopusers.domain.models.User;
import com.example.shopusers.domain.repository.UserRepository;
import com.example.shopusers.domain.valueobjects.FullName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final UserRepository userRepository;

    @PostConstruct
    public void initData() {
        User user1 = User.build("maja.vuevska", "maja123", FullName.make("Maja", "Vuevska"));
        User user2 = User.build("test.test", "test123", FullName.make("Test", "Test"));
    }
}