package org.example.repository.impl;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.model.entity.Category;
import org.example.model.entity.Product;
import org.example.repository.CategoryRepository;
import org.example.repository.session.SessionManager;
import org.example.repository.session.SessionManagerImpl;
import org.example.repository.sql_queries.CategorySqlQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Getter
@Data
public class CategoryRepositoryImpl implements CategoryRepository {

    private final SessionManager sessionManager;

    public CategoryRepositoryImpl() {
        this.sessionManager = new SessionManagerImpl();
    }


    @SneakyThrows
    @Override
    public List<Category> findAll() {
        List<Category> categories;
        sessionManager.beginSession();
        try (Connection connection = sessionManager.getCurrentSession();
             PreparedStatement pst = connection.prepareStatement(CategorySqlQuery.FIND_ALL_CATEGORIES.getQuery())) {
            try (ResultSet rs = pst.executeQuery()) {
                categories = parseCategoryFromResultSet(rs);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            sessionManager.rollbackSession();
            throw ex;
        }
        return categories;
    }

    @SneakyThrows
    @Override
    public Optional<Category> findById(Long id) {
        Category category;
        sessionManager.beginSession();
        try (Connection connection = sessionManager.getCurrentSession();
             PreparedStatement pst = connection.prepareStatement(CategorySqlQuery.FIND_ONE_CATEGORY.getQuery())) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                category = parseCategoryFromResultSet(rs).get(0);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            sessionManager.rollbackSession();
            throw ex;
        }
        return Optional.ofNullable(category);
    }

    @SneakyThrows
    @Override
    public Category save(Category category) {
        sessionManager.beginSession();
        try (Connection connection = sessionManager.getCurrentSession();
             PreparedStatement pst = connection.prepareStatement(CategorySqlQuery.SAVE_CATEGORY.getQuery())) {
            pst.setString(1, category.getName());
            pst.executeUpdate();
            sessionManager.commitSession();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            sessionManager.rollbackSession();
            throw ex;
        }
        return category;
    }


    private List<Category> parseCategoryFromResultSet(ResultSet rs) throws SQLException {
        final List<Product> products = new ArrayList<>();
        final Map<Long, Category> map = new HashMap<>();
        long previousId = -1L;

        while (rs.next()) {
            Long currentId = rs.getLong("id");
            Category category = map.get(currentId);
            if (Optional.ofNullable(category).isEmpty()) {
                Optional.ofNullable(map.get(previousId)).ifPresent(cat -> {
                    cat.setProducts(new ArrayList<>(products));
                });
                products.clear();
                category = Category.builder()
                        .id(currentId)
                        .name(rs.getString("category_name"))
                        .build();
            }
            products.add(Product.builder()
                    .name(rs.getString("product_name"))
                    .quantity(rs.getInt("quantity"))
                    .cost(rs.getInt("cost"))
                    .categoryId(currentId)
                    .build());
            map.put(currentId, category);
            previousId = rs.getLong("id");
        }
        Optional.ofNullable(map.get(previousId)).ifPresent(cat -> {
            cat.setProducts(new ArrayList<>(products));
        });
        return map.values().stream().toList();
    }
}
