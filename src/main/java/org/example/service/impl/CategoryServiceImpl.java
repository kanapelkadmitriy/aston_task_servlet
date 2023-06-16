package org.example.service.impl;

import lombok.Data;
import org.example.mapper.CategoryMapper;
import org.example.model.dto.CategoryDto;
import org.example.model.entity.Category;
import org.example.repository.CategoryRepository;
import org.example.repository.impl.CategoryRepositoryImpl;
import org.example.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Data
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper mapper;
    private CategoryRepository repository;

    public CategoryServiceImpl() {
        this.mapper = new CategoryMapper();
        this.repository = new CategoryRepositoryImpl();
    }

    public CategoryServiceImpl(CategoryRepository repository) {
        this.mapper = new CategoryMapper();
        this.repository = repository;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = repository.findAll();
        return mapper.convertToDtoList(categories);
    }

    @Override
    public CategoryDto findById(Long id) {
        final Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("category with id %s not found", id)));
        return mapper.convertToDto(category);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        repository.save(mapper.convertToModel(categoryDto));
        return categoryDto;
    }
}
