package com.example.productcatalog.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Money;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
public class Product extends AbstractEntity<ProductId> {

    private String productName;

    private int sales = 0;

    private Money price;

    private Product() {
        super(ProductId.randomId(ProductId.class));
    }

    public static Product build(String productName, Money price, int sales) {
        Product product = new Product();
        product.price = price;
        product.productName = productName;
        product.sales = sales;
        return product;
    }

    public void addSales(int quantity) {
        this.sales = this.sales - quantity;
    }

    public void removeSales(int quantity) {
        this.sales -= quantity;
    }
}
