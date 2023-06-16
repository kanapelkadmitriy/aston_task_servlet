package org.example.service;

import lombok.Data;
import lombok.SneakyThrows;
import org.example.TestUtil;
import org.example.model.dto.CategoryDto;
import org.example.model.entity.Category;
import org.example.repository.CategoryRepository;
import org.example.repository.impl.CategoryRepositoryImpl;
import org.example.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class CategoryServiceTest {

    private final CategoryRepository repository;
    private final CategoryService categoryService;

    public CategoryServiceTest() {
        this.repository = Mockito.mock(CategoryRepositoryImpl.class);
        this.categoryService = new CategoryServiceImpl(repository);
    }

    @Test
    @SneakyThrows
    public void successfulFindAll() {
        final List<Category> mockCategories = TestUtil.createCategoryList();
        Mockito.when(repository.findAll()).thenReturn(mockCategories);
        List<CategoryDto> actualCategories = categoryService.findAll();
        Assertions.assertEquals(actualCategories.size(), mockCategories.size());
        for (int i = 0; i < mockCategories.size(); i++) {
            Assertions.assertEquals(actualCategories.get(i).getName(), mockCategories.get(i).getName());
        }
    }

    @Test
    @SneakyThrows
    public void successfulFindById() {
        final Category mockCategory = TestUtil.createCategory();
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(mockCategory));
        final CategoryDto actualCategory = categoryService.findById(1L);
        Assertions.assertEquals(actualCategory.getName(), mockCategory.getName());
        Assertions.assertEquals(actualCategory.getProducts().size(), mockCategory.getProducts().size());
        for (int i = 0; i < mockCategory.getProducts().size(); i++) {
            Assertions.assertEquals(
                    actualCategory.getProducts().get(i).getName(),
                    mockCategory.getProducts().get(i).getName()
            );
        }
    }
}
