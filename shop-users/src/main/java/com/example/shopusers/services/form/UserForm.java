package com.example.shopusers.services.form;

import com.example.shopusers.domain.valueobjects.FullName;
import lombok.Data;

@Data
public class UserForm {
    private String username;
    private String password;
    private FullName fullName;
}
