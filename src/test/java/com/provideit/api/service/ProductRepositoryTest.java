package com.provideit.api.service;
/*
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.provideit.model.Product;

@SpringBootTest
@Testcontainers
class ProductRepositoryTest {
    @Container
    @ServiceConnection
    private static final MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0");

    @Autowired
    private final ProductRepository repository;

    ProductRepositoryTest(ProductRepository repository) {
        this.repository = repository;
    }

    Product p1, p2, p3;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        p1 = repository.save(
            new Product(1L, "title", 10.0, "category", "desc", "image"));
    }

    @Test
    void testSomething() {
        List<Product> test = repository.findAll();
        // assertThat(p2.)
    }
}

 */

