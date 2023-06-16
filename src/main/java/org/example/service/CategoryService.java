package org.example.service;

import org.example.model.dto.CategoryDto;

import java.sql.SQLException;
import java.util.List;


public interface CategoryService {

    List<CategoryDto> findAll() throws SQLException;

    CategoryDto findById(Long id);

    CategoryDto save(CategoryDto categoryDto);
}
