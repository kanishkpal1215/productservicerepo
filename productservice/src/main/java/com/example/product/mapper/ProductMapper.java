package com.example.product.mapper;

import com.example.product.dto.ProductDto;
import com.example.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);
    Product fromDto(ProductDto dto);
}
