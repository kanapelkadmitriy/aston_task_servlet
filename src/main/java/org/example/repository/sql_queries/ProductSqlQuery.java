package org.example.repository.sql_queries;

import lombok.Getter;

@Getter
public enum ProductSqlQuery {
    FIND_ALL_PRODUCTS("SELECT p.id, p.product_name, p.cost, p.quantity, c.characteristic_name FROM product AS p " +
            "LEFT JOIN link_product_characteristic AS pc ON p.id = pc.product_id " +
            "LEFT JOIN characteristic AS c ON pc.characteristic_id = c.id "),
    FIND_ONE_PRODUCT("SELECT p.id, p.product_name, p.cost, p.quantity, c.characteristic_name FROM product AS p " +
            "LEFT JOIN link_product_characteristic AS pc ON p.id = pc.product_id " +
            "LEFT JOIN characteristic AS c ON pc.characteristic_id = c.id " +
            "WHERE p.id = ?"),
    SAVE_PRODUCT("INSERT INTO product(id,category_id,product_name,cost,quantity) VALUES((SELECT MAX(id) FROM product)+1,?,?,?,?)");

    final String query;

    ProductSqlQuery(String QUERY) {
        this.query = QUERY;
    }

}
