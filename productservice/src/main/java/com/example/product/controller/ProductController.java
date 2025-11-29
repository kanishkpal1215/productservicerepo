package com.example.product.controller;


import com.example.product.service.ProductServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.product.dto.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductServiceInterface svc;

    public ProductController(ProductServiceInterface svc) { this.svc = svc; }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(svc.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") UUID id) {
        try {
            ProductDto product = svc.findById(id);
            return ResponseEntity.ok(product);
        } catch (com.example.product.exception.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        ProductDto created = svc.create(dto);
        return ResponseEntity.created(URI.create("/api/products/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable UUID id, @Valid @RequestBody ProductDto dto) {
        try {
            Optional<ProductDto> updated = svc.update(id, dto);
            if (updated.isPresent()) {
                return ResponseEntity.ok(updated.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (com.example.product.exception.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patch(@PathVariable UUID id, @RequestBody ProductDto dto) {
        try {
            Optional<ProductDto> patched = svc.patch(id, dto);
            if (patched.isPresent()) {
                return ResponseEntity.ok(patched.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (com.example.product.exception.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}