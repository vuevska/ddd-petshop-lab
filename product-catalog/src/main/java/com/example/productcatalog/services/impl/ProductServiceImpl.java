package com.example.productcatalog.services.impl;

import com.example.productcatalog.domain.exceptions.ProductNotFoundException;
import com.example.productcatalog.domain.models.Product;
import com.example.productcatalog.domain.models.ProductId;
import com.example.productcatalog.domain.repository.ProductRepository;
import com.example.productcatalog.services.ProductService;
import com.example.productcatalog.services.form.ProductForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findById(ProductId id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product createProduct(ProductForm form) {
        Product p = Product.build(form.getProductName(),form.getPrice(),form.getSales());
        productRepository.save(p);
        return p;
    }

    @Override
    public Product orderItemCreated(ProductId productId, int quantity) {
        Product p = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        p.addSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Product orderItemRemoved(ProductId productId, int quantity) {
        Product p = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        p.removeSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Product orderItemUpdated(ProductId productId, int quantity) {
        Product p = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        p.addSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
