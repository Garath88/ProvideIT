package com.provideit.api.model;

import static com.provideit.api.service.ProductService.REMOVE_DESCRIPTION_FILTER;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;

@Entity
@JsonFilter(REMOVE_DESCRIPTION_FILTER)
public final class Product {
    @jakarta.persistence.Id
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("category")
    private String category;
    @JsonProperty("description")
    private String description;
    @JsonProperty("image")
    private String image;

    @JsonCreator
    public Product(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("price") Double price,
        @JsonProperty("category") String category,
        @JsonProperty("description") String description,
        @JsonProperty("image") String image
    ) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    //Default constructor for repository
    Product() {
    }

    public String title() {
        return title;
    }

    public Double price() {
        return price;
    }

    public String category() {
        return category;
    }

    public String description() {
        return description;
    }

    public String image() {
        return image;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
            .append(id)
            .append(title)
            .append(price)
            .append(category)
            .append(description)
            .append(image)
            .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product other) {
            return new EqualsBuilder()
                .append(id, other.id)
                .append(title, other.title)
                .append(price, other.price)
                .append(category, other.category)
                .append(description, other.description)
                .append(image, other.image)
                .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return "{\"title\":\""
            + title
            + "\",\"price\":"
            + price
            + ",\"category\":\""
            + category
            + "\",\"description\":\""
            + description
            + "\",\"image\":\""
            + image
            + "\"}";
    }
}
