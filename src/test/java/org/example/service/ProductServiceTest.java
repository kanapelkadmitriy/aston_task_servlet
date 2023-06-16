package org.example.service;

import lombok.SneakyThrows;
import org.example.TestUtil;
import org.example.model.dto.ProductDto;
import org.example.model.entity.Product;
import org.example.repository.ProductRepository;
import org.example.repository.impl.ProductRepositoryImpl;
import org.example.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class ProductServiceTest {

    private final ProductRepository repository;
    private final ProductService categoryService;

    public ProductServiceTest() {
        this.repository = Mockito.mock(ProductRepositoryImpl.class);
        this.categoryService = new ProductServiceImpl(repository);
    }

    @Test
    @SneakyThrows
    public void successfulFindAll() {
        final List<Product> mockProducts = TestUtil.createProductList();
        Mockito.when(repository.findAll()).thenReturn(mockProducts);
        List<ProductDto> actualProducts = categoryService.findAll();
        Assertions.assertEquals(actualProducts.size(), mockProducts.size());
        for (int i = 0; i < mockProducts.size(); i++) {
            Assertions.assertEquals(actualProducts.get(i).getName(), mockProducts.get(i).getName());
        }
    }

    @Test
    @SneakyThrows
    public void successfulFindById() {
        final Product mockProduct = TestUtil.createProduct();
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(mockProduct));
        final ProductDto actualProduct = categoryService.findById(1L);
        Assertions.assertEquals(actualProduct.getName(), mockProduct.getName());
        Assertions.assertEquals(actualProduct.getCharacteristics().size(), mockProduct.getCharacteristics().size());
        for (int i = 0; i < mockProduct.getCharacteristics().size(); i++) {
            Assertions.assertEquals(
                    actualProduct.getCharacteristics().get(i).getName(),
                    mockProduct.getCharacteristics().get(i).getName()
            );
        }
    }
}
