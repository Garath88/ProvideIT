package com.provideit.api.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.provideit.api.model.Product;
import com.provideit.api.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductsController {
    ProductService productService;

    ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/detail")
    public ResponseEntity<List<Product>> getDetail() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<MappingJacksonValue> getAllProducts(
        @PageableDefault(page = 0, size = 8) Pageable pageable,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice,
        @RequestParam(required = false) String category) {
        MappingJacksonValue products = productService.findAll(pageable, minPrice, maxPrice, category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}