package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.exception.NotFoundException;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService implements ProductServiceInterface {
    private final ProductRepository repo;
    private final ProductMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository repo, ProductMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<ProductDto> findAll() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public ProductDto findById(UUID id) {
        return repo.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
    }

    @Transactional
    public ProductDto create(ProductDto dto) {
        log.info("Creating product: {}", dto);
        Product p = mapper.fromDto(dto);
        Product saved = repo.save(p);
        log.info("Product created with ID: {}", saved.getId());
        return mapper.toDto(saved);
    }

    @Transactional
    public Optional<ProductDto> update(UUID id, ProductDto dto) {
        log.info("Updating product with ID: {}", id);
        return repo.findById(id).map(existing -> {
            Product updatedProduct = mapper.fromDto(dto);
            updatedProduct.setId(existing.getId());
            Product updated = repo.save(updatedProduct);
            log.info("Product updated with ID: {}", updated.getId());
            return mapper.toDto(updated);
        });
    }

    @Transactional
    public Optional<ProductDto> patch(UUID id, ProductDto dto) {
        log.info("Patching product with ID: {}", id);
        return repo.findById(id).map(existing -> {
            if (dto.getName() != null) existing.setName(dto.getName());
            if (dto.getType() != null) existing.setType(dto.getType());
            if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
            if (dto.getColor() != null) existing.setColor(dto.getColor());
            if (dto.getPrice() != null) existing.setPrice(dto.getPrice());
            if (dto.getRating() != null) existing.setRating(dto.getRating());
            if (dto.getInventoryCount() != null) existing.setInventoryCount(dto.getInventoryCount());
            if (dto.getSizes() != null && !dto.getSizes().isEmpty()) existing.setSizes(dto.getSizes());
            Product updated = repo.save(existing);
            log.info("Product patched with ID: {}", updated.getId());
            return mapper.toDto(updated);
        });
    }

    @Transactional
    public void delete(UUID id) {
        log.info("Deleting product with ID: {}", id);
        if (!repo.existsById(id)) {
            log.warn("Product not found for deletion with ID: {}", id);
            throw new NotFoundException("Product not found with id: " + id);
        }
        repo.deleteById(id);
        log.info("Product deleted with ID: {}", id);
    }
}