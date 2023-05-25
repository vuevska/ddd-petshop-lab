package com.example.shopusers.domain.repository;

import com.example.shopusers.domain.models.User;
import com.example.shopusers.domain.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserId> {
}
