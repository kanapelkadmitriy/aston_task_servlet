package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.example.model.dto.CategoryDto;
import org.example.model.dto.ProductDto;
import org.example.service.ProductService;
import org.example.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/products")
public class AllProductsServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() {
        this.productService = new ProductServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productService.findAll());
        req.getRequestDispatcher("/jsp/find_all_products_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, Object> requestBody = new ObjectMapper().readValue(req.getReader(), HashMap.class);
        final String name = (String) Optional.ofNullable(requestBody.get("name"))
                .orElseThrow(() -> new RuntimeException("имя товара не передано"));
        final Integer categoryId = (Integer) Optional.of(requestBody.get("categoryId"))
                .orElseThrow(() -> new RuntimeException("категория товара не передана"));
        final Integer quantity = (Integer) Optional.of(requestBody.get("quantity"))
                .orElseThrow(() -> new RuntimeException("количество товара не передана"));
        final Integer cost = (Integer) Optional.of(requestBody.get("cost"))
                .orElseThrow(() -> new RuntimeException("Цена товара не передана"));
        final ProductDto savedProduct = productService.save(
                ProductDto.builder()
                        .name(name)
                        .categoryId(categoryId.longValue())
                        .cost(cost)
                        .quantity(quantity)
                        .build());
        req.setAttribute("product", savedProduct);
        req.getRequestDispatcher("/jsp/save_product_page.jsp").forward(req, resp);

    }
}
