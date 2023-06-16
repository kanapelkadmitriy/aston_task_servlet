package org.example.service;

import org.example.model.dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<ProductDto> findAll() throws SQLException;

    ProductDto findById(Long id);

    ProductDto save(ProductDto productDto);
}
