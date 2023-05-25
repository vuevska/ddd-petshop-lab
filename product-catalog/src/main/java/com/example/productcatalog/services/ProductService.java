package com.example.productcatalog.services;

import com.example.productcatalog.domain.models.Product;
import com.example.productcatalog.domain.models.ProductId;
import com.example.productcatalog.services.form.ProductForm;

import java.util.List;

public interface ProductService {

    Product findById(ProductId id);
    Product createProduct(ProductForm form);
    Product orderItemCreated(ProductId productId, int quantity);
    Product orderItemRemoved(ProductId productId, int quantity);
    Product orderItemUpdated(ProductId productId, int quantity);
    List<Product> getAll();

}
