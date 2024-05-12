package com.provideit.api.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.provideit.api.client.FakeStoreApiClient;
import com.provideit.api.model.Product;

import jakarta.persistence.criteria.Predicate;

@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    public static final String REMOVE_DESCRIPTION_FILTER = "RemoveDescriptionFilter";
    private final ProductRepository productRepository;

    ProductService(FakeStoreApiClient fakeStoreHttpClient, ProductRepository productRepository) {
        this.productRepository = productRepository;
        List<Product> ret = findAll();
        if (ret.isEmpty()) {
            LOGGER.info("Repository is empty");
            List<Product> products = fakeStoreHttpClient.getProducts();
            LOGGER.info("Retrieved {} items from API", products.size());
            List<Product> savedItems = this.productRepository.saveAll(products);
            LOGGER.info("Stored {} items in product repository", savedItems.size());
        }
    }

    public List<Product> findAll() {
        LOGGER.info("Fetching data from repository");
        return productRepository.findAll()
            .stream()
            .toList();
    }

    public MappingJacksonValue findAll(Pageable pageable, Double minPrice, Double maxPrice, String category) {
        Specification<Product> spec = buildSpecification(minPrice, maxPrice, category);
        Page<Product> products = productRepository.findAll(spec, pageable);
        return removeDescriptionFromProducts(products);
    }

    private Specification<Product> buildSpecification(Double minPrice, Double maxPrice, String category) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if (category != null) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private MappingJacksonValue removeDescriptionFromProducts(Page<Product> products) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("description");
        FilterProvider filters = new SimpleFilterProvider().addFilter(REMOVE_DESCRIPTION_FILTER, filter);
        MappingJacksonValue mapping = new MappingJacksonValue(products);
        mapping.setFilters(filters);
        return mapping;
    }

}
