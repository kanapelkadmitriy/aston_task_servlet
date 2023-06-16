package org.example;

import org.example.model.entity.Category;
import org.example.model.entity.Characteristic;
import org.example.model.entity.Product;

import java.util.List;

public class TestUtil {

    public static Category createCategory() {
        return Category.builder()
                .id(1L)
                .name("computers")
                .products(List.of(
                        Product.builder()
                                .name("laptop1")
                                .quantity(1)
                                .cost(1)
                                .build(),
                        Product.builder()
                                .name("laptop2")
                                .quantity(2)
                                .cost(2)
                                .build()
                ))
                .build();
    }

    public static List<Category> createCategoryList() {
        return List.of(
                Category.builder()
                        .name("computers")
                        .products(List.of(
                                Product.builder()
                                        .name("laptop1")
                                        .quantity(1)
                                        .cost(1)
                                        .build(),
                                Product.builder()
                                        .name("laptop2")
                                        .quantity(2)
                                        .cost(2)
                                        .build()
                        ))
                        .build(),
                Category.builder()
                        .name("food")
                        .products(List.of(
                                Product.builder()
                                        .name("burger")
                                        .quantity(1)
                                        .cost(1)
                                        .build(),
                                Product.builder()
                                        .name("pizza")
                                        .quantity(2)
                                        .cost(2)
                                        .build()
                        ))
                        .build());
    }

    public static Product createProduct() {
        return Product.builder()
                .name("laptop_1")
                .categoryId(1L)
                .quantity(1)
                .cost(1)
                .characteristics(List.of(
                        Characteristic.builder()
                                .name("characteristic_1")
                                .build(),
                        Characteristic.builder()
                                .name("characteristic_2")
                                .build()
                ))
                .build();
    }

    public static List<Product> createProductList() {
        return List.of(
                Product.builder()
                        .name("laptop_1")
                        .categoryId(1L)
                        .quantity(1)
                        .cost(1)
                        .characteristics(List.of(
                                Characteristic.builder()
                                        .name("characteristic_1")
                                        .build(),
                                Characteristic.builder()
                                        .name("characteristic_2")
                                        .build()
                        ))
                        .build(),
                Product.builder()
                        .name("laptop_2")
                        .categoryId(2L)
                        .quantity(2)
                        .cost(2)
                        .characteristics(List.of(
                                Characteristic.builder()
                                        .name("characteristic_3")
                                        .build(),
                                Characteristic.builder()
                                        .name("characteristic_4")
                                        .build()
                        ))
                        .build());
    }
}
