package com.example.product.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;


public class ProductDto {
    private final UUID id;
    private final String type;
    private final String name;
    private final String description;
    private final String color;
    private final Double rating;
    private final Set<@NotBlank(message = "Size cannot be blank") String> sizes;
    private final BigDecimal price;
    private final Integer inventoryCount;

    public ProductDto(UUID id, String type, String name, String description, String color, Double rating, Set<String> sizes, BigDecimal price, Integer inventoryCount) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.color = color;
        this.rating = rating;
        this.sizes = sizes == null ? new java.util.LinkedHashSet<>() : sizes;
        this.price = price;
        this.inventoryCount = inventoryCount;
    }

    public UUID getId() { return id; }
    public String getType() { return type; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getColor() { return color; }
    public Double getRating() { return rating; }
    public Set<String> getSizes() { return sizes == null ? new java.util.LinkedHashSet<>() : sizes; }
    public BigDecimal getPrice() { return price; }
    public Integer getInventoryCount() { return inventoryCount; }
}