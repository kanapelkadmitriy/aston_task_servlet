package org.example.mapper;

import lombok.AllArgsConstructor;
import org.example.model.dto.CategoryDto;
import org.example.model.entity.Category;

import java.util.List;

@AllArgsConstructor
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    private final ProductMapper productMapper = new ProductMapper();

    @Override
    public Category convertToModel(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .products(productMapper.convertToModelList(categoryDto.getProducts()))
                .build();
    }

    @Override
    public CategoryDto convertToDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .products(productMapper.convertToDtoList(category.getProducts()))
                .build();
    }

    @Override
    public List<Category> convertToModelList(List<CategoryDto> categoryDtos) {
        return categoryDtos.stream()
                .map(this::convertToModel)
                .toList();
    }

    @Override
    public List<CategoryDto> convertToDtoList(List<Category> categories) {
        return categories.stream()
                .map(this::convertToDto)
                .toList();
    }
}
