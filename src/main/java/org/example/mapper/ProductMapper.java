package org.example.mapper;

import lombok.AllArgsConstructor;
import org.example.model.dto.ProductDto;
import org.example.model.entity.Product;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductMapper implements Mapper<Product, ProductDto> {

    private final CharacteristicMapper characteristicMapper = new CharacteristicMapper();

    @Override
    public Product convertToModel(ProductDto productDto) {
        return Product.builder()
                .categoryId(productDto.getCategoryId())
                .name(productDto.getName())
                .quantity(productDto.getQuantity())
                .cost(productDto.getCost())
                .characteristics(characteristicMapper.convertToModelList(productDto.getCharacteristics()))
                .build();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .categoryId(product.getCategoryId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .cost(product.getCost())
                .characteristics(characteristicMapper.convertToDtoList(product.getCharacteristics()))
                .build();
    }

    @Override
    public List<Product> convertToModelList(List<ProductDto> productDtos) {
        return productDtos == null ? new ArrayList<>() : productDtos.stream().map(this::convertToModel).toList();
    }

    @Override
    public List<ProductDto> convertToDtoList(List<Product> products) {
        return products == null ? new ArrayList<>() : products.stream().map(this::convertToDto).toList();
    }
}
