package com.example.product.service;

import com.example.product.dto.ProductDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductServiceInterface {
    List<ProductDto> findAll();
    ProductDto findById(UUID id);
    ProductDto create(ProductDto dto);
    Optional<ProductDto> update(UUID id, ProductDto dto);
    Optional<ProductDto> patch(UUID id, ProductDto dto);
    void delete(UUID id);
}