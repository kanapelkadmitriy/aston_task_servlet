package org.example.repository.impl;

import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.model.entity.Characteristic;
import org.example.model.entity.Product;
import org.example.repository.ProductRepository;
import org.example.repository.session.SessionManager;
import org.example.repository.session.SessionManagerImpl;
import org.example.repository.sql_queries.ProductSqlQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Getter
@Data
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionManager sessionManager;

    public ProductRepositoryImpl() {
        this.sessionManager = new SessionManagerImpl();
    }

    @SneakyThrows
    @Override
    public List<Product> findAll() {
        List<Product> products;
        sessionManager.beginSession();
        try (Connection connection = sessionManager.getCurrentSession();
             PreparedStatement pst = connection.prepareStatement(ProductSqlQuery.FIND_ALL_PRODUCTS.getQuery())) {
            try (ResultSet rs = pst.executeQuery()) {
                products = parseProductFromResultSet(rs);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            sessionManager.rollbackSession();
            throw ex;
        }
        return products;
    }

    @SneakyThrows
    @Override
    public Optional<Product> findById(Long id) {
        Product category;
        sessionManager.beginSession();
        try (Connection connection = sessionManager.getCurrentSession();
             PreparedStatement pst = connection.prepareStatement(ProductSqlQuery.FIND_ONE_PRODUCT.getQuery())) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                category = parseProductFromResultSet(rs).get(0);
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
    public Product save(Product product) {
        sessionManager.beginSession();
        try (Connection connection = sessionManager.getCurrentSession();
             PreparedStatement pst = connection.prepareStatement(ProductSqlQuery.SAVE_PRODUCT.getQuery())) {
            pst.setLong(1, product.getCategoryId());
            pst.setString(2, product.getName());
            pst.setInt(3, product.getCost());
            pst.setInt(4, product.getQuantity());
            pst.executeUpdate();
            sessionManager.commitSession();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            sessionManager.rollbackSession();
            throw ex;
        }
        return product;
    }

    private List<Product> parseProductFromResultSet(ResultSet rs) throws SQLException {
        final List<Characteristic> characteristics = new ArrayList<>();
        final Map<Long, Product> map = new HashMap<>();
        long previousId = -1L;

        while (rs.next()) {
            Long currentId = rs.getLong("id");
            Product product = map.get(currentId);
            if (Optional.ofNullable(product).isEmpty()) {
                Optional.ofNullable(map.get(previousId)).ifPresent(pr -> {
                    pr.setCharacteristics(new ArrayList<>(characteristics));
                });
                characteristics.clear();
                product = Product.builder()
                        .id(currentId)
                        .name(rs.getString("product_name"))
                        .cost(rs.getInt("cost"))
                        .quantity(rs.getInt("quantity"))
                        .build();
            }
            characteristics.add(Characteristic.builder()
                    .name(rs.getString("characteristic_name"))
                    .build());
            map.put(currentId, product);
            previousId = rs.getLong("id");
        }
        Optional.ofNullable(map.get(previousId)).ifPresent(pr -> {
            pr.setCharacteristics(new ArrayList<>(characteristics));
        });
        return map.values().stream().toList();
    }
}
