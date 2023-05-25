package com.example.productcatalog.config;

import com.example.productcatalog.domain.models.Product;
import com.example.productcatalog.domain.repository.ProductRepository;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final ProductRepository productRepository;

    @PostConstruct
    public void initData() {
        Product product1 = Product.build("Dog Food", Money.valueOf(Currency.MKD, 250), 10);
        Product product2 = Product.build("Cat Toy", Money.valueOf(Currency.MKD, 90), 5);
        Product product3 = Product.build("Cat Litter", Money.valueOf(Currency.MKD, 130), 15);
        if (productRepository.findAll().isEmpty()) {
            productRepository.saveAll(Arrays.asList(product1, product2, product3));
        }
    }
}
