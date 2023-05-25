package com.example.productcatalog.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Quantity implements ValueObject {

    private final int quantity;

    protected Quantity() {
        this.quantity = 0;
    }

}
