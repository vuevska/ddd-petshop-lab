package com.example.shopusers.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class FullName implements ValueObject {
    private String firstName;
    private String lastName;

    protected FullName() {
        this.firstName = null;
        this.lastName = null;
    }

    public FullName(@NonNull String firstName, @NonNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static FullName make(String firstName, String lastName) {
        return new FullName(firstName, lastName);
    }

    public void changeFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void changeLastName(String lastName) {
        this.lastName = lastName;
    }
}