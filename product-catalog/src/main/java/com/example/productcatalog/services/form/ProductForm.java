package com.example.productcatalog.services.form;

import com.example.sharedkernel.domain.financial.Money;
import lombok.Data;

@Data
public class ProductForm {

    private String productName;
    private Money price;
    private int sales;
}
