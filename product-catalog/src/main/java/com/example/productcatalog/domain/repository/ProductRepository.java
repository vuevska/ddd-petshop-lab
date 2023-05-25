package com.example.productcatalog.domain.repository;

import com.example.productcatalog.domain.models.Product;
import com.example.productcatalog.domain.models.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
