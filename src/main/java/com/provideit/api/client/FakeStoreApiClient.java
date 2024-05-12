package com.provideit.api.client;

import java.util.List;

import org.springframework.web.service.annotation.GetExchange;

import com.maciejwalkowiak.spring.http.annotation.HttpClient;
import com.provideit.api.model.Product;

@HttpClient("fakestore-client")
public interface FakeStoreApiClient {
    @GetExchange
    List<Product> getProducts();
}
