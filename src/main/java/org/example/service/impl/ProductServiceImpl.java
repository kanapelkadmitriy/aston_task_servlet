package org.example.service.impl;

import lombok.Data;
import org.example.mapper.ProductMapper;
import org.example.model.dto.ProductDto;
import org.example.model.entity.Product;
import org.example.repository.ProductRepository;
import org.example.repository.impl.ProductRepositoryImpl;
import org.example.service.ProductService;

import java.sql.SQLException;
import java.util.List;

@Data
public class ProductServiceImpl implements ProductService {

    private ProductMapper mapper;
    private ProductRepository repository;

    public ProductServiceImpl() {
        this.mapper = new ProductMapper();
        this.repository = new ProductRepositoryImpl();
    }

    public ProductServiceImpl(ProductRepository repository) {
        this.mapper = new ProductMapper();
        this.repository = repository;
    }

    @Override
    public List<ProductDto> findAll() throws SQLException {
        List<Product> categories = repository.findAll();
        return mapper.convertToDtoList(categories);
    }

    @Override
    public ProductDto findById(Long id) {
        final Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("product with id %s not found", id)));
        return mapper.convertToDto(product);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        repository.save(mapper.convertToModel(productDto));
        return productDto;
    }
}
