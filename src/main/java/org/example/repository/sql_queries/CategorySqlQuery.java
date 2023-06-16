package org.example.repository.sql_queries;

import lombok.Getter;

@Getter
public enum CategorySqlQuery {

    FIND_ALL_CATEGORIES("SELECT c.id, c.category_name, p.product_name, p.cost, p.quantity FROM category AS c " +
            "LEFT JOIN product AS p ON p.category_id = c.id "),
    FIND_ONE_CATEGORY("SELECT c.id, c.category_name, p.product_name, p.cost, p.quantity FROM category AS c " +
            "LEFT JOIN product AS p ON p.category_id = c.id " +
            "WHERE c.id = ?"),
    SAVE_CATEGORY("INSERT INTO category(id,category_name) VALUES((SELECT MAX(id) FROM category)+1, ?)");

    final String query;

    CategorySqlQuery(String QUERY) {
        this.query = QUERY;
    }
}
