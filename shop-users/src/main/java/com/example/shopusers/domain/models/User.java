package com.example.shopusers.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.shopusers.domain.valueobjects.FullName;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
@Getter
public class User extends AbstractEntity<UserId> {
    private String username;
    private String password;
    private FullName fullName;

    private User() {
        super(UserId.randomId(UserId.class));
    }

    public static User build(String username, String password, FullName fullName) {
        User user = new User();
        user.username = username;
        user.password = password;
        user.fullName = fullName;
        return user;
    }

}
